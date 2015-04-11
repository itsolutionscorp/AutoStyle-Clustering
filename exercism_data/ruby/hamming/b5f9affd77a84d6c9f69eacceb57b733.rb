module Hamming
  class << self
    def compute dna1, dna2
      minLength = [dna1, dna2].min { |a, b| a.length <=> b.length }.length

      #Calculating the difference between two strings
      dna1[0...minLength].each_char.zip(dna2.each_char).count { |a, b| a != b }
    end
 end
end 
