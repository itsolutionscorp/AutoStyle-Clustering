class Triplet

	def initialize(*triplet)
		@triplet = triplet
	end

	def sum
		@triplet.reduce(:+)
	end

	def product
		@triplet.reduce(:*)
	end

	def pythagorean?
		@triplet[0]**2 + @triplet[1]**2 == @triplet[2]**2
	end
	
	def self.where(min_factor: 1, max_factor: 1, sum: nil)
		[*(min_factor..max_factor)].combination(2).map do |a,b|
			c = Math.sqrt(a**2 + b**2).floor
			next if c > max_factor

			triplet = Triplet.new(a, b, c)
			triplet if triplet.pythagorean? && (!sum || triplet.sum == sum)
		end.compact
	end

end
