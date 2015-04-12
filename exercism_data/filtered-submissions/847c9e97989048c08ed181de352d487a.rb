class Hamming

  def compute(dna1, dna2)
    dna_array1 = dna1.split(//)
    arr_index1 = 0
    dna_array2 = dna2.split(//)
    arr_index2 = 0
    hamming_distance = 0

    (dna_array1.size).times do
      if dna_array1[arr_index1] == dna_array2[arr_index2]
        arr_index1 += 1
        arr_index2 += 1
      else
        hamming_distance += 1
        arr_index1 += 1
        arr_index2 += 1
      end
    end
    return hamming_distance
  end

end
