def compute(dna1, dna2)


    min_length = [dna1.chars.length, dna2.chars.length].min
    dna1 = dna1.chars[0,min_length]
    dna2 = dna2.chars[0,min_length]


    comp_array = dna1 + dna2


    hamming_distance = 0
    comp_array.each_index do |i|
      if comp_array[i+min_length] && comp_array[i] != comp_array[i+min_length]
        hamming_distance += 1
      end
    end


    hamming_distance

  end