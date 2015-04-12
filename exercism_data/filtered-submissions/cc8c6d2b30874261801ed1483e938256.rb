class Hamming

  def compute (strand1, strand2)
    hamming_difference = 0
    # Loop through every character in the first array
    strand1.split("").each_with_index{|character, index|
      if strand2.length > index
      	# Add the absolute value of the difference between the
      	# two characters (-1, 0, or 1)
      	hamming_difference += (character <=> strand2[index]).abs
  	  end
    }
    return hamming_difference
  end

end
