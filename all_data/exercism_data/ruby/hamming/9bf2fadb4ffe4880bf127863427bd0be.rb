class Hamming
	def self.compute(strand1, strand2)
		base_pairs(strand1, strand2).map { |base1, base2|
			pair_distance(base1, base2)
		}.reduce(&:+)
	end

	private

	def self.pair_distance(base1, base2)
		base1 == base2 ? 0 : 1
	end

	def self.base_pairs(strand1, strand2)
		if strand1.size != strand2.size
			raise ArgumentError.new("Strands need to be the same length")
		end

		Enumerator.new do |base_pair|
			for base_index in 0..(strand1.size - 1)
				base_pair << [strand1[base_index], strand2[base_index]]
			end
		end
	end
end
