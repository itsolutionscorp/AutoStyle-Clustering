class Robot
	def initialize() @name = getName end
	attr_reader :name
	def reset() @name = getName end
	def getName() [*'A'..'Z'].sample(2).join + [*100..999].sample.to_s end
end
