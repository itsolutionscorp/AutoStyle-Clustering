class Complement
  RNA = 'RNA'
  DNA = 'DNA'
  COMPLEMENT_OF_RNA_MAPPING = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'U',
      'U' => 'A'
  }
  COMPLEMENT_OF_DNA_MAPPING = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'T' => 'A'
  }

  def self.of_dna(dna_strand)
    strand_complement(dna_strand, DNA)
  end

  def self.of_rna(rna_strand)
    strand_complement(rna_strand, RNA)
  end

  private

  def self.strand_complement(strand, type)
    strand_array = strand.split('')
    complement_array = strand_array.map do |nucleotide|
      mapping_name = "COMPLEMENT_OF_#{type}_MAPPING"
      mapping = Kernel.const_get(mapping_name)
      mapping[nucleotide]
    end
    complement_array.join
  end
end
