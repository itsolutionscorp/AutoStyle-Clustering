class DNA
  attr_accessor :nucleotides

  NUCLEOTIDES = %w{A T C G U}
  DNA = NUCLEOTIDES - ['U']

  def initialize(nucleotides)
    self.nucleotides = nucleotides
  end

  def count(nucleotide)
    validate! nucleotide
    nucleotides.count nucleotide
  end

  def nucleotide_counts
    Hash.new.tap do |hash|
      DNA.each do |nucleotide|
        hash[nucleotide] = count nucleotide
      end
    end
  end

  private
  def validate!(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
  end
end
