def compute(strand_1, strand_2)
    counter = 0
    strand_1_array = strand_1.split('')
    strand_2_array = strand_2.split('')
    if strand_1_array.length <= strand_2_array.length
      shorter_array = strand_1_array
      longer_array = strand_2_array
    else
      shorter_array = strand_2_array
      longer_array = strand_1_array
    end
    shorter_array.each_with_index do |letter, index|
      counter += 1 if letter != longer_array[index]
    end
    counter
  end