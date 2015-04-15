class Complement

  DNA_TO_RNA = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

  def self.of_dna(strand)
    self.complement(strand, DNA_TO_RNA)
  end

  def self.of_rna(strand)
    self.complement(strand, RNA_TO_DNA)
  end

  def self.complement(strand, mapping)
    strand.chars.map do |nuc|
      mapping[nuc] || raise("Unexpected nucleotide: #{nuc} (expected #{mapping.keys.inspect})")
    end.join
  end

end
