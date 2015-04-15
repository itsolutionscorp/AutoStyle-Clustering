require 'prime'

class Fixnum
	def factorize
		num = self
		factors = []
		Prime.each(num) do |p|
			break unless num
			while(num % p == 0) do
				factors << p
				num /= p
			end
		end
		factors
	end
end

module Raindrops
	NUM_MAP = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def self.convert(num)
		content = num.factorize.uniq.collect do |factor|
					NUM_MAP[factor]
				  end.compact.join
		content.empty? ? num.to_s : content
	end
end
