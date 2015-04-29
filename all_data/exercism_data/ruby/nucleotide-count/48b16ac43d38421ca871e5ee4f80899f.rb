class DNA
  def initialize sequence
    fail ArgumentError unless valid_sequence? sequence
    @sequence = sequence
  end

  def count char
    return 0 if char == "U" # Handle first because this is not valid DNA.
    fail ArgumentError unless valid_sequence? char
    @sequence.chars.select { |x| x == char } .count
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G'),
    }
  end

  private # Shh! This method doesn't exist!
  def valid_sequence? sequence
    !(sequence =~ /[^ATCG]/)
  end
end
