class DNA
  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    nucleotide_counts.fetch(nucleotide, 0)
  end

  def nucleotide_counts
    count_hash = default_counts
    each_nucleotide { |nucleotide| count_hash[nucleotide] += 1}
    count_hash
  end

private

  def each_nucleotide
    dna_string.chars.each do |char|
      yield char
    end
  end

  def default_counts
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def validate_nucleotide(nucleotide)
    raise ArgumentError unless %w[A G C T U].include?(nucleotide)
  end

end
