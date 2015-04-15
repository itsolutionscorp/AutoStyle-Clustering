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
		triplets = [*min_factor..max_factor].combination(2).map do |a,b|
			c = Math.sqrt(a**2 + b**2).floor
			Triplet.new(a, b, c) unless c > max_factor
		end.compact
		
		triplets = triplets.select { |t| t.sum == sum } if sum
		triplets.select(&:pythagorean?)
	end

end
