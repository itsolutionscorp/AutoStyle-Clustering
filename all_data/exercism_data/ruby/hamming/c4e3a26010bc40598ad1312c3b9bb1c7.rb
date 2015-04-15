class Hamming
	def self.compute(strand1,strand2)
		hamming = 0
		(0..shortest_length(strand1,strand2) - 1).each do |i|
			hamming += 1 unless strand1[i] == strand2[i]
		end
		hamming
	end

	private
	def self.shortest_length(strand1,strand2)
		strand1.size < strand2.size ? strand1.size : strand2.size
	end 
end
