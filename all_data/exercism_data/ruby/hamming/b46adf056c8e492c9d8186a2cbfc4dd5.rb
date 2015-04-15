class Hamming
	def self.compute strand1, strand2
    iterations = [strand1.length, strand2.length].min

    distance = 0

    iterations.times do |index|
      distance += 1 unless strand1[index] == strand2[index]
    end

    distance
	end
end
