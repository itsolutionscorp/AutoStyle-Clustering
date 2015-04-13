def compute (strand1, strand2)
    hamming_difference = 0

    strand1.split("").each_with_index{|character, index|
      if strand2.length > index


      	hamming_difference += (character <=> strand2[index]).abs
  	  end
    }
    return hamming_difference
  end