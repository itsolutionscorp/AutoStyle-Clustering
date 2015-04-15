# class Hamming
#   def self.compute(first_strand, second_strand)
#     min_length = [first_strand.length, second_strand.length].min
#     counter = 0

#     # if first_strand.eql?(second_strand)
#     #   return counter
#     # end
#     # return counter if first_strand == second_strand

#     min_length.times do |number|
#       # Check if we have something
#       # if first_strand[number] != nil && second_strand[number] != nil
#         # Compare the chars
#         if first_strand[number] != second_strand[number]
#           counter = counter+1
#           # counter+=1
#         end
#       # end
#     end
#     return counter
#   end
# end

class Hamming
  def self.compute(first_strand, second_strand)
    counter = 0
    [first_strand.length, second_strand.length].min.times do |number|
      counter+=1 unless first_strand[number] == second_strand[number]
    end
    return counter
  end
end
