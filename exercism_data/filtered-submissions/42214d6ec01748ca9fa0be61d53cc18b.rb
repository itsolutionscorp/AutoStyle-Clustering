def compute(dna1, dna2)

    #remove extra characters
    min_length = [dna1.chars.length, dna2.chars.length].min
    dna1 = dna1.chars[0,min_length]
    dna2 = dna2.chars[0,min_length]

    #cobine dna strings for testing
    comp_array = dna1 + dna2

    #compare each corresponding nucleotide 
    hamming_distance = 0
    comp_array.each_index do |i|
      if comp_array[i+min_length] && comp_array[i] != comp_array[i+min_length]
        hamming_distance += 1
      end
    end

    #return total distance
    hamming_distance

  end