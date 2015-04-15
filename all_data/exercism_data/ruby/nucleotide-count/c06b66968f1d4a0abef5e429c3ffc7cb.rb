class DNA
  def initialize string
    raise ArgumentError unless /\A[ATCG]*\z/ =~ string
    @string = string
  end

  def count nucleotide
    raise ArgumentError unless "ACTGU".include? nucleotide
    @string.chars.count nucleotide
  end

  def nucleotide_counts
    Hash[['A', 'T', 'C', 'G'].map { |n| [n, count(n)] }]
  end
end
