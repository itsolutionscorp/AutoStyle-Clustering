def compute(str1,str2)


    shortest_strand_length = str1.length <= str2.length ? str1.length : str2.length
    hamming_distance = 0


    (0...shortest_strand_length).each do |i|
      hamming_distance += 1 unless str1[i] == str2[i]
    end

    hamming_distance
  end