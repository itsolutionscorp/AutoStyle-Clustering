class DNA

  def initialize(nucleotide)
    @nucleotide = nucleotide
  end

  def to_rna
    parse_dna.gsub(/T/, 'U')
  end

  def parse_dna
    strand = ""
    @nucleotide.split('').each do |nucleotide|
      strand += nucleotide
    end
    strand
  end

end
