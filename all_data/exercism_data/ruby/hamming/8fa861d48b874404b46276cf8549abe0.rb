class Hamming

	def self.compute(ham_one,ham_two)
		hamming = 0

		(0..shortest_length(ham_one,ham_two)-1).each do |i|
			hamming += 1 unless ham_one[i]==ham_two[i]
		end

		hamming
	end

	private

		def self.shortest_length(ham_one,ham_two)
			ham_one.size < ham_two.size ? ham_one.size : ham_two.size
		end

end
