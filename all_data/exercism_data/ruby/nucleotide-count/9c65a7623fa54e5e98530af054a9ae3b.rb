class DNA

  def initialize(dna_strand)
    @strand = dna_strand
    validate @strand
  end 

  def valid_dna_nucleotides
    %w[A T C G]
  end

  def count(nucleotide)
    validate nucleotide unless nucleotide == 'U'
    @strand.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotides = Hash.new
    valid_dna_nucleotides.each {|k| @nucleotides[k] = count(k)}
    @nucleotides
  end

  def validate (nucleotide)
    nucleotide.chars.each do |nuc|
      raise ArgumentError unless valid_dna_nucleotides.include?(nuc)
    end
  end

end
