class Hamming

    def self.compute(strand1, strand2)
        i = 0
        hamming_distance = 0
        length = (strand1.length < strand2.length) ? strand1.length : strand2.length
	while i < length
      	    hamming_distance += (strand1[i] == strand2[i]) ? 0 : 1
            i += 1
        end
        return hamming_distance
    end

end


# puts Hamming.compute('AT', 'AT')
