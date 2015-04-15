class DNA
  NUCLEOTIDES = { adenine: "A", thymine: "T", cytosine: "C", guanine: "G", uracil: "U" }

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.values.include? nucleotide
    @sequence.count nucleotide
  end

  def nucleotide_counts
    bases_for_dna.each_with_object(Hash.new(0)) do |pair,result|
      base = pair.last
      result[base] = @sequence.count base
    end
  end

  def bases_for_dna
    NUCLEOTIDES.select {|n| n != :uracil}
  end
end
