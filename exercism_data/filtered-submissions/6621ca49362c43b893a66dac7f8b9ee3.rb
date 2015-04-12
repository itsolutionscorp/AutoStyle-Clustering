def compute(firstStrand, secondStrand)
    max_hamming_distance = [firstStrand.size, secondStrand.size].min

    ham_distance = 0
    max_hamming_distance.times do |index|
      ham_distance += 1 if firstStrand[index] != secondStrand[index]
    end

    ham_distance
  end