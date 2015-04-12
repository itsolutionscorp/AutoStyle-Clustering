module Hamming

  def compute(strand1, strand2)

    array1 = strand1.split('')
    array2 = strand2.split('')
    min_size = [array1.size,array2.size].min
    counter = 0
    (0...min_size).each do |n|
      counter += 1 if array1[n] != array2[n]
    end
    counter
  end

end
