class Complement

  TO_RNA_HASH = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  TO_DNA_HASH = TO_RNA_HASH.invert
  
  def self.of_dna(dna_strand)
    dna_strand.chars.map { |nucl| TO_RNA_HASH[nucl] }.join
  end
  
  def self.of_rna(rna_strand)
    rna_strand.chars.map { |nucl| TO_DNA_HASH[nucl] }.join
  end
end
