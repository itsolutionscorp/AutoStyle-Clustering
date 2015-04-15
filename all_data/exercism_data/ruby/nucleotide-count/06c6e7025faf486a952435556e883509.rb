class DNA
  def initialize(string_representation)
    @string_representation = string_representation
  end

  def count(nucleotide)
    raise ArgumentError unless 'ATCGU'.include?(nucleotide)
    return 0 if nucleotide == 'U'
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    initial_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    nucleotides.each_with_object(initial_counts){|nucleotide, counts| counts[nucleotide] += 1 }
  end

  private

  def nucleotides
    @string_representation.chars
  end
end
