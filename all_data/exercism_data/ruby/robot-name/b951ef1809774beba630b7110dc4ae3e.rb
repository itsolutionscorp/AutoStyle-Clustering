#!/usr/bin/env ruby

class Robot
	#assigns a robot name when a new robot object is created
	def initialize
		@robot_name = assign_name
	end	

	#name method returns the instance variable @robot_name for the robot being called
	def name
		return @robot_name	
	end
	
	#assign_name method is called to assign a new name to a robot
	def assign_name
		robot_name = String.new		
	
		robot_name << (0..255).map(&:chr).select{|x| x =~ /[a-zA-Z]/}.sample(2).join
		robot_name << (0..255).map(&:chr).select{|x| x =~ /[0-9]/}.sample(3).join
		
		
		puts robot_name
		return robot_name
	end
	
	#reset method can be called to reset a robot's name
	def reset
		@robot_name = assign_name
	end
end
