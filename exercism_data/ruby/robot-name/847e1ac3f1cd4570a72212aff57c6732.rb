class Robot
	def initialize() @name = getName end
	attr_reader :name
	def reset() @name = getName end
	def getName() [*65..90].sample.chr + [*65..90].sample.chr + [*100..999].sample.to_s end
end
