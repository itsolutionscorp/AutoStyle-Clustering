class Hamming
	def initialize(strand1, strand2)
		@strand1, @strand2 = strand1, strand2
	end

	def compute
		min_length = [@strand1, @strand2].map(&:size).min
		diff_count = 0
		(0...min_length).each do |index|
			if @strand1[index] != @strand2[index]
				diff_count += 1
			end
		end
		diff_count
	end

	def self.compute(strand1, strand2)
		self.new(strand1, strand2).compute
	end
end
