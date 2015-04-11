class Hamming
	class << self
		def compute (strand_a, strand_b)
			a = strand_a.split("")
			b = strand_b.split("")

			min_length = [a.length, b.length].min

			diffs = 0

			for i in 0..min_length - 1
				if a[i] != b[i]
					diffs += 1
				end
			end

			diffs

		end
	end
end	
