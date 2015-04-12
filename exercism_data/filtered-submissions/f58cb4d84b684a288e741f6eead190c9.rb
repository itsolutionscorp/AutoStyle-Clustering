class Hamming
    def compute(st1, st2)
        i = 0
        hamming_distance = 0
        length = (st1.length < st2.length) ? st1.length : st2.length
	while i < length
      	    hamming_distance += (st1[i] == st2[i]) ? 0 : 1
            i += 1
        end
        return hamming_distance
    end
end
