class Robot
	def initialize
	end

	def name
		letters = (0..1).map { rand(65..90).chr }.join
		numbers = rand(100..999).to_s
		let_num = letters + numbers
		@let_num ||= let_num
	end

	def reset
		@let_num = nil
	end
end
