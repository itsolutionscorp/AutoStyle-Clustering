class Complement

  DNA_TO_RNA = ['CGTA', 'GCAU']
  RNA_TO_DNA = DNA_TO_RNA.reverse

  def self.of_dna(dna)
    self.complement(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    self.complement(rna, RNA_TO_DNA)
  end

  def self.complement(strand, mapping)
    validate_nucleotides(strand, mapping[0])
    strand.tr(*mapping)
  end

  def self.validate_nucleotides(strand, mapping_source)
    unexpected = strand.chars.to_a.uniq - mapping_source.chars.to_a.uniq
    unless unexpected.empty?
      raise(UnexpectedNucleotide,
        "Unexpected nucleotides: #{unexpected} (expected #{mapping_source})")
    end
  end

  class UnexpectedNucleotide < StandardError; end
end
