class Hamming
	def self.minimum_length(strand1, strand2)
    [strand1.length, strand2.length].min
	end
	def self.distance(strand1, strand2, minlength)
		distance = 0

    (0..minlength - 1).each do |i|
			distance += 1 unless strand1[i].eql? strand2[i]
    end

		distance
	end
	def self.compute(strand1,strand2) 
		minlength = self.minimum_length strand1, strand2

		self.distance(strand1, strand2, minlength)
 	end
end
