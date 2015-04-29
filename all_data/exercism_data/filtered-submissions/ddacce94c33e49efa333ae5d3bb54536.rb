def compute(string1, string2)
    string1_array = string1.split(//)
    string2_array = string2.split(//)

    if string1_array.length < string2_array.length
      shorter_strand = string1_array
    else
      shorter_strand = string2_array
    end

    hamming_distance = 0
    i = 0
    while i < shorter_strand.length
      if string1_array[i] != string2_array[i]
        hamming_distance += 1
      end
      i += 1
    end
    hamming_distance
  end