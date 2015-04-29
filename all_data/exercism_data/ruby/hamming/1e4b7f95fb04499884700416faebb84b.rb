module Hamming
  class << self
    def compute dna1, dna2
      minLength = minimum_strings_length dna1, dna2
      difference_between_strings dna1[0...minLength], dna2[0...minLength]
    end

    private
      def minimum_strings_length string1, string2
        minLength = [string1, string2].min { |a, b| a.length <=> b.length }.length
      end

      def difference_between_strings string1, string2
         string1.each_char.zip(string2.each_char).count { |a, b| a != b }
      end
  end
end 
