class Hamming
  # Method to count the number of differences between 2 strings char by char
  # originally intended for the calculation of the 'Hamming distance'.
  # Will stop at the shortest string length and ignore the rest
  def self.compute(a,b)
    # create arrays of both strings to allow stepping through each char
    aa = a.split('')
    ba = b.split('')

    # running total for the hamming distance. Start off assuming none then add to it
    h  = 0

    # Step through each char to ru a comparison
    (aa.length > ba.length ? ba.length : aa.length).times do |i|
    
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
