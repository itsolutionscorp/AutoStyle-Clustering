def compute(strand1, strand2)
    arr1 = strand1.split('')
    arr2 = strand2.split('')
    arr1.map.with_index { |char, i| arr2[i] != char ? 1 : 0 }.reduce(:+)
  end