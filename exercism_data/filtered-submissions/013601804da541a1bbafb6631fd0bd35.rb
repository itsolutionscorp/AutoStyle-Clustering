class Hamming
  # Method to count the number of differences between 2 strings char by char
  # originally intended for the calculation of the 'Hamming distance'.
  # Will stop at the shortest string length and ignore the rest
  def compute(a,b)
    # create arrays of both strings to allow stepping through each char
    aa = a.split('')
    ba = b.split('')

    # running total for the hamming distance. Start off assuming none then add to it
    h  = 0

    # Create a numeric range to target in turn the array indices up till the shortest one
    index_range = (0..((aa.length > ba.length ? ba.length : aa.length)-1))

    # Now step through that range to compare each char in the 2 sequences in turn
    (0..((aa.length > ba.length ? ba.length : aa.length)-1)).each do |i|
      # If they are not the same...
      if ba[i] != aa[i]
        # increment our distance running total
        h += 1
      else
        # no need to implement changes - skip to the next one
        next
      end
    end
    # Return the hamming distance
    return h
  end
end
