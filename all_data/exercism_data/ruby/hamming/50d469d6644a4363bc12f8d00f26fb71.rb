module Hamming
  class << self   
    def compute dna1, dna2
      minLength = minimumStringsLength dna1, dna2
      differenceBetweenStrings dna1.take(minLength), dna2.take(minLength)
    end

    private
      def minimumStringsLength string1, string2
        minLength = [string1, string2].min { |a, b| a.length <=> b.length }.length
      end

      def differenceBetweenStrings string1, string2
         dna1.each_char.zip(dna2.each_char).count { |a, b| a != b }
      end
  end
end 
