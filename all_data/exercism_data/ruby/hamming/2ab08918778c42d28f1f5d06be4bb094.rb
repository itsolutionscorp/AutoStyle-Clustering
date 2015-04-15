# Compute the Hamming distance between two DNA strings

# ------------------------- Third iteration - block
# I like this one. Courtesy of seeing Andrew's code.
# class Hamming
#   def self.compute(arg1, arg2)
#     arg1.length.times.count {|i| arg1[i] != arg2[i]}
#   end
# end

# ------------------------- Second iteration - add each
class Hamming
  def self.compute(arg1, arg2)
    arg1.each_char.with_index.count {|char, index| char != arg2[index]}
  end

end

# # ------------------------- First iteration
# class Hamming
#   def self.compute(arg1, arg2)
#     i = 0
#     hamming_distance = 0
#     while i < arg1.length
#       if arg1[i] != arg2[i]
#         hamming_distance += 1
#       end
#       i += 1
#     end
#     hamming_distance
#   end
# end
