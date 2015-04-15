#
# The Hamming computes the Hamming distance between two DNA
# strands.  See http://en.wikipedia.org/wiki/Hamming_distance
#
class Hamming

  #
  # Return the Hamming distance between two DNA strands, each
  # represented as a string of nucleobases, e.g. 'GAGCCTAC'.  If
  # the strands have different lengths, the comparison will only
  # be up to the length of the shorter strand.
  #
  def self.compute(strand1, strand2)
    shorter_length = [strand1.length, strand2.length].min
    (0...shorter_length).count { |i| strand1[i] != strand2[i] }
  end

end
