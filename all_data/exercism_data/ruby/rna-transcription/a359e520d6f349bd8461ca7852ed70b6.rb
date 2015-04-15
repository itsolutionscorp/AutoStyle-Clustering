module Complement
  DNA_TO_RNA = {
   'G' => 'C',
   'C' => 'G',
   'T' => 'A',
   'A' => 'U' 
  }

  def self.of_rna(strand)
    translate strand.chars, DNA_TO_RNA.invert
  end
  def self.of_dna(strand)
    translate strand.chars, DNA_TO_RNA
  end
  def self.translate(chars,dictionary)
    chars.inject("") do |acc,char| 
      acc += dictionary[char] 
      acc
    end
  end
end
