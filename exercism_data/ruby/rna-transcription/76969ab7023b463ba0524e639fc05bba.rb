class Complement
  DNA_COMPLEMENT_MAP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMPLEMENT_MAP = DNA_COMPLEMENT_MAP.invert


  def self.of_dna(dna)
    generic_complement(dna, DNA_COMPLEMENT_MAP)
  end

  def self.of_rna(rna)
    generic_complement(rna, RNA_COMPLEMENT_MAP)
  end

  private
  def self.generic_complement(input, complement_map)
    input.chars.map{|nucl| 
      complement_map[nucl]}.join
  end
end
