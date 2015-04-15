class Robot

	def initialize
		@name
	end
	def name
		@name ||= (0...2).map{ rand(65..90).chr }.join + rand(100..999).to_s
		
	end

	def reset 
		@name = nil
	end

end
