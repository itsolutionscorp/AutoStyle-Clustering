def compute(dna1, dna2)
        distance = 0

        [dna1.length, dna2.length].min.times do |i|
            distance += 1 unless dna1[i] == dna2[i]
        end
        distance
    end