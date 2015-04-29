def compute(strand_1, strand_2)
    hamming = 0
    array_1 = strand_1.split('')
    array_2 = strand_2.split('')
    array_1.each_index do |i|
      break if array_1[i] == nil
      break if array_2[i] == nil
      hamming += 1 if array_1[i] != array_2[i]
    end
    hamming
  end