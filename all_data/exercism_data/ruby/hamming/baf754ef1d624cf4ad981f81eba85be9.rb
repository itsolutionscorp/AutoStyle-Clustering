class Hamming
	def self.compute strand1, strand2
    iterations = strand1.length
    iterations = strand2.length unless strand2.length > strand1.length

    distance = 0

    (0..iterations - 1).each do |index|
      distance += 1 unless strand1[index] == strand2[index]
    end

    distance
	end
end
