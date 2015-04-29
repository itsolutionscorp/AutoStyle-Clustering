class SumOfMultiples
	attr_reader :multiples
	def initialize(*multiples)
		@multiples = multiples
	end
	def self.to(num)
		factors = []
		sum = 0
		[3,5].each do |multiple|
			(1...num).each do |no|
				if no%multiple == 0
					factors << no
				end
			end
		end
		factors.uniq.each { |factor| sum+=factor}
		sum
	end
	def to(num)
		factors = []
		sum = 0
		multiples.each do |multiple|
			(1...num).each do |no|
				if no%multiple == 0
					factors << no
				end
			end
		end
		factors.uniq.each { |factor| sum+=factor}
		sum
	end
end
