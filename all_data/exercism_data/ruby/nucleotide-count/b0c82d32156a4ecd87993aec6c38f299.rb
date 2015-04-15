class DNA

  def initialize(dna_string)
    @counts = default_counts
    generate_counts(dna_string)
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotides.include? nucleotide
    counts[nucleotide] || 0
  end

  def nucleotide_counts
    counts
  end

  private

  def default_counts
    dna_nucleotides.each_with_object({}) do |nucleotide, count|
      count[nucleotide] = 0
    end
  end

  def counts
    @counts
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

  def generate_counts(dna_string)
    dna_string.split("").each do |nucleotide|
      raise ArgumentError unless dna_nucleotides.include? nucleotide
      @counts[nucleotide] += 1 
    end
  end

end
