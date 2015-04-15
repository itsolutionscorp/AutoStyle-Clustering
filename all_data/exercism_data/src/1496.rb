def compute(dna1, dna2)
        hamming = 0
        [dna1.size,dna2.size].min.times { |i|
            hamming+=1 if dna1[i] != dna2[i]
        }
        return hamming
    end

end