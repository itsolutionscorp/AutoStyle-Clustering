class Robot 
	def initialize
		@saved_name = (0...2).map { (65 + rand(26)).chr }.join.to_s + (0...3).map { (rand(9)) }.join.to_s
	end

	def name
		@saved_name
	end

	def reset
		@saved_name = (0...2).map { (65 + rand(26)).chr }.join.to_s + (0...3).map { (rand(9)) }.join.to_s
	end
end
