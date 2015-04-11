class DNA
  attr_reader   :nucleotides

  NUCLEOTIDE_KEYS = %w(A T C G)

  def initialize(dna_string)
    raise ArgumentError unless valid_dna_string?(dna_string)
    @nucleotides = parse_dna(dna_string)
  end

  def count(type)
    raise ArgumentError if type == 'X'
    nucleotides.fetch(type) { 0 }
  end

  def nucleotide_counts
    Hash[NUCLEOTIDE_KEYS.map { |x| [x, 0] }].merge(nucleotides)
  end

  private

  def valid_dna_string?(str)
    (Array(str.chars) - NUCLEOTIDE_KEYS).empty?
  end

  def parse_dna(str)
    Hash[str.chars.group_by(&:to_s).map { |k, v| [k, v.count] }]
  end
end
