class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError if !"ATCGU".include? nucleotide
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    "ATCG".split('').inject(Hash.new(0)) { |a, b| a[b] = count(b); a }
  end
end
