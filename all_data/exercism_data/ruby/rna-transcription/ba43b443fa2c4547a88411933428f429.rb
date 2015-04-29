require 'pry'
#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
class Complement

  @@dna_to_rna_table= {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U' 
  }
    
  def self.of_dna(strand)

    complements = ""

    strand.chars.each do |nucleotide| 
      complements += @@dna_to_rna_table.fetch(nucleotide) { raise ArgumentError }
    end
    
    complements 
  end

  def self.of_rna(strand)
    complements = ""
    
    strand.chars.each do |nucleotide| 
      complements += @@dna_to_rna_table.invert.fetch(nucleotide) { raise ArgumentError }
    end

    complements 
  end

end
