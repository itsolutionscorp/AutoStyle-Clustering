class SumOfMultiples
	attr_reader :multiples
	def initialize(*multiples)
		@multiples = multiples
	end
	def self.to(num)
		new(3,5).to(num)
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
