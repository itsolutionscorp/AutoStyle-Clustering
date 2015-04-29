class Robot

	def name
		@robot_name ||= "#{(65 + rand(26)).chr * 2}" + "#{rand(900) + 100}"
	end

	def reset
		remove_instance_variable :@robot_name
	end

end
