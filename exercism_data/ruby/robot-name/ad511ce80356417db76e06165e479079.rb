class Robot
	def initialize
		@name
	end

	def name
		@name ||= ('A'..'Z').to_a.sample(2).join + rand(100..999).to_s
	end

	def reset
		@name = nil
	end
end
