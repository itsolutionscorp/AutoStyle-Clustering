# Hamming.rb
# Developed by: iaserrat
# 09/25/2014
# This file and all of its contents are under public domain.

class Hamming
  # Call this method (Hamming.compute) to calculate the hamming difference between two DNA strands. 
  # Params:
  # +strand1+:: DNA Strand as string.
  # +strand2+:: DNA Strand as string.
  def self.compute(strand1, strand2)
    distance = 0
    # Trivial case
    if strand1 == strand2
      return distance
    end
    # Determine smallest string
    max = strand1.length > strand2.length ? strand2.length : strand1.length
    # Compare strings and update distance
    for i in 0..max - 1
      # Is each character in string equal? Add 0 else 1.
      distance += strand1[i] == strand2[i] ? 0 : 1;
    end
    return distance
  end

end
