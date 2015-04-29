# first exercise of exercism.io in Ruby
# Author: Jeramy Singleton
# Date: 23-SEP-2014
# -------------------------------------

class Hamming

  def self.compute(strand1, strand2)
    return 0 if strand1 == strand2

    distance = 0
    length = length_to_compare(strand1, strand2)
    (0...length).each do |x|
      distance += 1 if strand1[x] != strand2[x]
    end

    distance
  end #end compute

  def self.length_to_compare(strand1, strand2)
    return strand1.length if strand1.length <= strand2.length
    return strand2.length if strand2.length <= strand1.length
  end #end length_to_compare

end #end class
