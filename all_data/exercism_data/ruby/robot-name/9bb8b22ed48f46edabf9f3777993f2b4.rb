class Robot
	attr_reader :name
	@@used = []
	def initialize
		@name = name_generate
	end
	def reset
		@name = name_generate
	end
	def self.used_names
		@@used
	end
	
	private def name_generate
		# letters part
		l =* ('A'..'Z')
		nm = ""
		2.times { nm += l[rand(26)] }
		# numbers part
		3.times { nm += rand(10).to_s }
		if @@used.include?(nm)
			name_generate
		end
		@@used << nm
		nm
	end
end
