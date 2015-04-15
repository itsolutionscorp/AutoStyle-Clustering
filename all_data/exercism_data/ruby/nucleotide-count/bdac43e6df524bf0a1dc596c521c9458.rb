class DNA
  ALPHABETH = %w[A T C G]

  def initialize(string)
    @string = string
    raise ArgumentError unless valid?
  end

  def count(nucleotide)
    raise ArgumentError unless ALPHABETH.include? nucleotide
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Hash[ALPHABETH.map { |e| [e, count(e)] }]
  end

  private

  def valid?
    @string =~ /^#{Regexp.union(ALPHABETH)}*$/
  end
end
