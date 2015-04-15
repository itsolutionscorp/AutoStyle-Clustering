class DNA

  attr_reader :strand

  def initialize(strand)
    if valid_dna?(strand)
      @strand = strand
    else
      raise ArgumentError,
        "Sorry, not everything in that strand is a valid nucleotide"
    end
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotides.include? nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    strand.chars.each_with_object(default_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def default_counts
    dna_nucleotides.each_with_object({}) do |nucleotide, count|
      count[nucleotide] = 0
    end
  end

  def valid_dna?(strand)
    strand.chars.all? { |contender| dna_nucleotides.include? contender }
  end

  def dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def rna_nucleotides
    ['A', 'C', 'G', 'U']
  end

  def nucleotides
    dna_nucleotides | rna_nucleotides
  end

end
