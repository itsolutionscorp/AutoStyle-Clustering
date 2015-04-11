class DNA < String
  def initialize sequence
    fail ArgumentError if sequence =~ /[^ATCG]/
    super
  end

  def count char
    fail ArgumentError unless %w(A T C G U).include? char
    chars.select { |x| x == char } .count
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G'),
    }
  end
end
