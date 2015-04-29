class Complement
  DNA_TO_RNA= { 'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U' }

  def self.of_rna(strand)
    new(strand).of_rna
  end

  def self.of_dna(strand)
    new(strand).of_dna
  end

  def initialize(strand)
    @strand = strand
  end

  def of_rna
    remap_strand { |nucleotide| rna_complement(nucleotide) } 
  end

  def of_dna
    remap_strand { |nucleotide| dna_complement(nucleotide) } 
  end

  def remap_strand(&block)
    @strand.chars.map do |nucleotide|
      yield nucleotide
    end.join ''
  end

  def dna_complement(nucleotide)
    DNA_TO_RNA[nucleotide]
  end

  def rna_complement(nucleotide)
    DNA_TO_RNA.invert[nucleotide]
  end
end
