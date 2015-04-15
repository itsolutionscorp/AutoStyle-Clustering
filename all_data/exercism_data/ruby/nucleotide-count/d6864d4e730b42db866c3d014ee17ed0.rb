class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide? nucleotide

    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    dna_nucleotides.each_with_object(Hash.new(0)) do |nucleotide, memo|
      memo[nucleotide] = count(nucleotide)
    end
  end

  private

  def valid_nucleotide?(nucleotide)
    nucleotides.include? nucleotide
  end

  def nucleotides
    @nucleotides ||= dna_nucleotides | rna_nucleotides
  end

  def dna_nucleotides
    %w{A C G T}
  end

  def rna_nucleotides
    %w{A C G U}
  end
end
