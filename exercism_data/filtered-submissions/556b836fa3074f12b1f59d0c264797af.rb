def compute(dna, dnb)
    distance = 0

    min = [dna.length, dnb.length].min
    min.times do |i|
      if dna[i] != dnb[i]
        distance += 1
      end
    end

    distance
  end