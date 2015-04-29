module Hamming
  def self.compute(a, b)
    a = a.chars
    b = b.chars

    a, b = b, a if b.size < a.size

    a.zip(b).count { |x, y| x != y }
  end
end

# class Hamming
#   attr_accessor :mutation_count
#
#   def self.compute(original_dna, mutated_dna)
#     unless original_dna.length == mutated_dna.length
#       length_adjuster([original_dna, mutated_dna])
#     else
#       @original = original_dna.chars
#       @mutated = mutated_dna.chars
#     end
#     mutation_count
#   end
#
#   def self.length_adjuster(strands)
#     lengths = strands.sort_by {|strand| strand.length}
#     shorter_length = lengths[0].length
#     @original =  lengths[0]
#     @mutated = lengths[1].ljust(shorter_length)
#   end
#
#   def self.mutation_count
#     count = 0
#     @original.each_with_index do |acid, index|
#       unless @original[index] == @mutated[index]
#         count += 1
#       end
#     end
#     return count
#   end
#
#
# end
