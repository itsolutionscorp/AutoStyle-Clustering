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
						trip = Triplet.new(a, b, c)
						if trip.pythagorean?
							if sum && sum != trip.sum()
								# don't use it
							else
								triplets << trip 
							end
						end
					end
				end
			end
		end
	end
end
