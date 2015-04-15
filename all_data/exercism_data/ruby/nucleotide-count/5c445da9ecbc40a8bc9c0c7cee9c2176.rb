class Nucleotide
  NUCLEOTIDES = %w(A T C G)

  def self.from_dna dna
    validateDna dna
    new dna
  end

  def initialize strain
    @strain = strain.chars
  end

  def count x
    @strain.count x
  end

  def histogram
    NUCLEOTIDES.each_with_object({}) { |k, h| h[k] = count k }
  end

  private
  def self.validateDna dna
    dna.chars.each { |i| raise ArgumentError if !NUCLEOTIDES.include? i }
  end

end
