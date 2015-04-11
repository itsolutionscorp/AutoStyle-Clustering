# RNA Complementing class
class Complement
  RNA_COMPLEMENTS = { 'C' => 'G', 'A' => 'U', 'G' => 'C', 'T' => 'A' }
  DNA_COMPLEMENTS = { 'C' => 'G', 'U' => 'A', 'G' => 'C', 'A' => 'T' }

  def self.of_dna( string )
    complement( string, RNA_COMPLEMENTS )
  end

  def self.of_rna( string )
    complement( string, DNA_COMPLEMENTS )
  end

  def self.complement( string, complements )
    string.chars.map { |base| complements[base] }.join ''
  end
end
