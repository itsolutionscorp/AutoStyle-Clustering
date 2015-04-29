#
# The Hamming class is responsible for computing the Hamming distance
# (http://en.wikipedia.org/wiki/Hamming_distance) between two DNA strands.
#
class Hamming

  #
  # Return the Hamming distance between two DNA strands, each represented
  # as a string of nucleobases, e.g. 'GAGCCTACTAACGGGAT'.  If the strands
  # have different lengths, the comparison will only be up to the length
  # of the shorter strand.
  #
  def self.compute(strand1, strand2)
    hamming_distance = 0
    num_chars_to_compare = [strand1.length, strand2.length].min
    num_chars_to_compare.times do |i|
      hamming_distance += 1 if strand1[i] != strand2[i]
    end
    hamming_distance
  end

end


#
# The following Ruby-esque two-liners could be used, although I am not sure
# if they are easier to comprehend:
#
#   def self.computer(strand1, strand2)
#     num_chars_to_compare = [strand1.length, strand2.length].min
#     num_chars_to_compare.times.inject(0) {|distance, i| strand1[i] == strand2[i] ? distance : distance + 1}
#   end
#
# or:
#
#   def self.computer(strand1, strand2)
#     num_chars_to_compare = [strand1.length, strand2.length].min
#     ( num_chars_to_compare.times.select {|i| strand1[i] != strand2[i]} ).length
#   end
#
# If the two strings are the same length then we can drop to a one-liner:
#
#   def self.computer(strand1, strand2)
#     ( strand1.chars.zip(strand2.chars).select {|pair| pair[0] != pair[1] } ).length
#   end
#
# To handle strings of unequal length, we can enhance the one-liner to:
#
#   def self.computer(strand1, strand2)
#     ( strand1.chars.zip(strand2.chars).select {|p| (p[0] || p[1]) != (p[1] || p[0]) } ).length
#   end
#
# Here, though, we need to know that zip will pad with nil values if one array
# is shorter than the other, and the 'if' condition would definitely require
# additional explanation.  We could use regex-style multi-line comments:
#
#   def self.computer(strand1, strand2)
#     (
#       strand1                                 # e.g. 'GAGC'
#         .chars                                # ['G','A','G','C']
#         .zip(
#           strand2                             # e.g. 'GAT'
#             .chars                            # ['G','A','T']
#         )                                     # [['G','G'], ['A','A'], ['G','T'], ['C',nil]]
#         .select { |p|                         # p = ... ['C',nil]
#           (p[0] || p[1]) != (p[1] || p[0])    # ('C' || nil) != (nil || 'C') => 'C' != 'C' => false
#         }                                     # [['G','T']]
#     ).length                                  # 1
#   end
#
# But at this point the code is being more clever than clear.
#
