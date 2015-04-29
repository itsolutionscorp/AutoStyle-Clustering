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
		[].tap do |matches|
			[*(min_factor..max_factor)].combination(2) do |ab|
				[*((ab.max+1)..max_factor)].each do |c|
					triplet = Triplet.new(*ab, c)
					matches.push(triplet) if triplet.send("match?", sum)
				end
			end	
		end
	end

	private

		def match?(sum)
			sum ? pythagorean? && sum == self.sum : pythagorean?
		end

end
