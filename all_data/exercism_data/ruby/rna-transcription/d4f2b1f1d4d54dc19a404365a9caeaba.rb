class Complement
  RNA = 'RNA'
  DNA = 'DNA'
  COMPLEMENT_OF_RNA = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
  }
  COMPLEMENT_OF_DNA = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'U',
      'T' => 'A',
  }

  def self.of_dna(dna_strand)
    strand_complement(dna_strand, DNA)
  end

  def self.of_rna(rna_strand)
    strand_complement(rna_strand, RNA)
  end

  private

  def self.strand_complement(strand, type)
    complement_array = strand.chars.map do |nucleotide|
      mapping_name = "COMPLEMENT_OF_#{type}"
      mapping = Complement.const_get(mapping_name)
      mapping[nucleotide]
    end
    complement_array.join
  end
end
