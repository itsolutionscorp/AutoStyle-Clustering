class Hamming
  def self.compute(dna1, dna2)
    arr1 = dna1.each_char.to_a
    arr2 = dna2.each_char.to_a

    arr1.size < arr2.size ? difference(arr2, arr1) : difference(arr1, arr2)
  end

  def self.difference(larger_arr, smaller_arr)
    count = 0
    smaller_arr.each_with_index { |e, i| count += 1 if e != larger_arr[i] }
    count
  end
end
