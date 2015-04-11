# Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).
# Both DNA and RNA strands are a sequence of nucleotides.
# The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and thymine (**T**).
# The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and uracil (**U**).
#
# Given a DNA strand, its transcribed RNA strand is formed by replacing
# each nucleotide with its complement:
#
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

DNA_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
RNA_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

class Complement

   def self.argument_error(char)
      raise ArgumentError.new(char + " is not a valid input")
   end

   def self.of_dna(dna)
      rna = String.new
      dna.each_char do |char|
         if DNA_RNA.keys.include? char
            rna << DNA_RNA[char] 
         else
            self.argument_error(char)
         end
      end
      rna
   end

   def self.of_rna(rna)
      dna = String.new
      rna.each_char do |char|
         if RNA_DNA.keys.include? char
            dna << RNA_DNA[char] 
         else
            self.argument_error(char)
         end
      end
      dna
   end
end
