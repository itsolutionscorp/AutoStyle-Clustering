class DNA

  def initialize(dna_strand)
    @strand = dna_strand
    validate @strand
  end 

  def valid_nucleotides
    %w[A T C G]
  end

  def count(nucleotide)
    validate nucleotide unless nucleotide == 'U'
    @strand.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotides = Hash.new
    valid_nucleotides.each {|k| @nucleotides[k] = count(k)}
    @nucleotides
  end

  def validate (nucleotide)
    nucleotide.chars.each do |nuc|
      raise ArgumentError unless valid_nucleotides.include?(nuc)
    end
  end

end
