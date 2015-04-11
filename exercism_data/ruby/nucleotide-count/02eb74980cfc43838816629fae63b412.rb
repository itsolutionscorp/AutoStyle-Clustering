class DNA
  attr_accessor :nucleotides

  NUCLEOTIDES = %w{A T C G U}
  DNA = NUCLEOTIDES - ['U']

  def initialize(nucleotides)
    self.nucleotides = nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
    nucleotides.count nucleotide
  end

  def nucleotide_counts
    Hash.new.tap do |hash|
      DNA.each do |nucleotide|
        hash[nucleotide] = count nucleotide
      end
    end
  end
end
