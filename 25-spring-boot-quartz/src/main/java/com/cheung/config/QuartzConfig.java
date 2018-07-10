package com.cheung.config;

import com.cheung.quartz.MyJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * Spring Boot 整合 Quartz 配置类
 *
 * @author Cheung
 */
@Configuration
public class QuartzConfig {

	/**
	 * 1、创建Job对象
	 */
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		// 关联自定义Job类
		jobDetailFactoryBean.setJobClass(MyJob.class);// 注意：MyJob类的实例化是靠JobDetailFactoryBean来完成的。
		return jobDetailFactoryBean;
	}

	/**
	 * 2.1、创建Trigger对象 --> SimpleTriggerFactoryBean：简单的trigger
	 *
	 */
	/*@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
		SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
		// 关联JobDetail对象
		simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
		// 设置重复间隔(毫秒数)
		simpleTriggerFactoryBean.setRepeatInterval(2000);
		// 设置重复次数
		simpleTriggerFactoryBean.setRepeatCount(5);
		return simpleTriggerFactoryBean;
	}*/

	/**
	 * 2.2、创建Trigger对象 --> CronTriggerFactoryBean：cron表达式的trigger
	 *
	 */
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		// 关联JobDetail对象
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
		// 设置触发时间
		cronTriggerFactoryBean.setCronExpression("0/2 * * * * ?");
		return cronTriggerFactoryBean;
	}

	/**
	 * 3.1、创建Scheduler对象
	 */
	/*@Bean
	public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		// 关联Trigger对象
		schedulerFactoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
		return schedulerFactoryBean;
	}*/

	/**
	 * 3.2、创建Scheduler对象
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean,MyAdaptableJobFactory myAdaptableJobFactory) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		// 关联Trigger对象
		schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
		// 重新设置JobFactoryBean为自定义的MyAdaptableJobFactory
		// 让SchedulerFactoryBean去调用spring创建的MyAdaptableJobFactory，进而调用其重写后的createJobInstance()方法，完成将实例化的任务对象添加到springIOC容器，并且完成对象的注入
		schedulerFactoryBean.setJobFactory(myAdaptableJobFactory);
		return schedulerFactoryBean;
	}

}
