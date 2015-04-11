NUCLEOTIDES = %w[A C G T U]

class DNA
  NUCLEOTIDES_IN_DNA = NUCLEOTIDES - ['U']

  def initialize(dna_string)
    raise ArgumentError.new("it's not DNA String") unless dna_string =~ /^[#{NUCLEOTIDES_IN_DNA.join}]*$/

    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError.new("not allowed in DNA") unless NUCLEOTIDES.include?(nucleotide)

    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    Hash[ NUCLEOTIDES_IN_DNA.map { |n| [n, count(n)] } ]
  end

  private

  def nucleotides
    @nucleotides ||= @dna_string.split(//)
  end

end
