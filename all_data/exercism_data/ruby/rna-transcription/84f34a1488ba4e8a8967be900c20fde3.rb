class Complement
  DNA_TYPE = 'DNA'
  RNA_TYPE = 'RNA'

  def self.of_dna(dna_strand)
    strand_complement(dna_strand, DNA_TYPE)
  end

  def self.of_rna(rna_strand)
    strand_complement(rna_strand, RNA_TYPE)
  end

  private

  def self.strand_complement(strand, strand_type)
    strand_array = strand.split('')
    complement_array = strand_array.map { |nucleotide| nucleotide_complement(nucleotide, strand_type) }
    complement_array.join
  end

  def self.nucleotide_complement(nucleotide, strand_type)
    case nucleotide
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        strand_type == DNA_TYPE ? 'U' : 'T' # complement of A is U when transcribing DNA and U when transcribing RNA
      when 'U'
        'A'
    end
  end
end
