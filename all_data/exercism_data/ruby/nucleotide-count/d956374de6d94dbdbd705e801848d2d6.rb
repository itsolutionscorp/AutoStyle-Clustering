class DNA
  DNA_NUCLEOTIDES = %w{A C G T}
  RNA_NUCLEOTIDES = %w{A C G U}

  VALID_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES

  BLANK_RESULT = Hash[DNA_NUCLEOTIDES.zip(DNA_NUCLEOTIDES.map { 0 })]

  def initialize(dna_strand)
    nucleotides = dna_strand.chars
    @raw_counts = nucleotides.each_with_object(Hash.new(0)) do |nucleotide, counts|
      counts[nucleotide] += 1
    end

    unknown_keys = @raw_counts.keys - DNA_NUCLEOTIDES
    raise ArgumentError unless unknown_keys.empty?
  end

  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    BLANK_RESULT.merge(@raw_counts)
  end
end
