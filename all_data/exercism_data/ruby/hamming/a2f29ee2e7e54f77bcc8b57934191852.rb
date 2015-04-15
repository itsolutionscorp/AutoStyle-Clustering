class Hamming
  def self.compute(dna_1, dna_2)
    dna_enum = dna_2.chars.each
    dna_1.chars.count { |c| c != dna_enum.next }
  end
end




# class Hamming
#   def self.compute(dna_1, dna_2)
#     mismatches = 0
#      dna_1.size.times.each do |i|
#       mismatches += 1 if dna_1[i] != dna_2[i]
#     end 
#     mismatches
#   end
# end
# dna_1.size.times
