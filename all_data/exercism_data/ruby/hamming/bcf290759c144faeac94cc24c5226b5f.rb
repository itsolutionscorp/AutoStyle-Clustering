# Calculates the Hamming difference given two DNA sequences as strings

# Split each dna string into an array with each letter an element
# create a Hamming count variable
# for each letter in array_1 with index
#   add 1 to count if array_1[i] != array_2[i]
# end

# How to deal with sequences where first
#   nucleotides don't line up strand
# padding the strings
#
#
#
class Hamming
  def self.compute(strand_1, strand_2)
    hamming = 0
    array_1 = strand_1.split('')
    array_2 = strand_2.split('')
    array_1.each_index do |i|
      break if array_1[i] == nil
      break if array_2[i] == nil
      hamming += 1 if array_1[i] != array_2[i]
    end
    hamming
  end
end






# class Hamming
#   def self.compute(dna_1, dna_2)
#     array_1 = dna_1.split("")
#     array_2 = dna_2.split("")
#     hamming_count = 0

#     array_1.each_with_index do |c, i|
#       hamming_count += 1 if c != array_2[i]
#     end
#     hamming_count
#   end
# end
