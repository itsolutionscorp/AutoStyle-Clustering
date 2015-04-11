class Triplet
	attr_reader :sides
	
	def initialize(a, b, c)
		@sides = [a, b, c].sort
	end
	
	def sum()
		@sides.reduce(:+)
	end
	
	def product()
		@sides.reduce(:*)
	end
	
	def pythagorean?()
		@sides[0]**2 + @sides[1]**2 == @sides[2]**2
	end
	
	def self.where(min_factor: 1, max_factor: 10, sum: nil)
		[].tap do |triplets|
			(min_factor..max_factor).each do |a|
				(a..max_factor).each do |b|
					(b..max_factor).each do |c|
						next if sum && sum != a+b+c
						triplets << Triplet.new(a, b, c) if a**2 + b**2 == c**2
					end
				end
			end
		end
	end
end
