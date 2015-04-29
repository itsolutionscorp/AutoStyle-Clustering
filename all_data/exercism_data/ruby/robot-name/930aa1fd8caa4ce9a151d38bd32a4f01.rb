class Robot
	def initialize
	end

	def name
		@let_num ||= begin
			letters = (0..1).map { rand(65..90).chr }.join
			numbers = rand(100..999).to_s
			letters + numbers
		end
	end

	def reset
		@let_num = nil
	end
end
