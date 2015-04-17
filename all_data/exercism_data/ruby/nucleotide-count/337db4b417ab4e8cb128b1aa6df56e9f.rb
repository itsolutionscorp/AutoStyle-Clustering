class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %W(U)

  def initialize(chain)
    assert_nucleotides(chain)

    @chain = chain
    @breakdown = NucleotideBreakdown.from(chain)
  end

  def count(nucleotide)
    assert_nucleotides(nucleotide, against: ALL_NUCLEOTIDES)
    @breakdown.number_of(nucleotide)
  end

  def nucleotide_counts
    @breakdown.by_nucleotide
  end

  def assert_nucleotides(chain, args = {against: DNA_NUCLEOTIDES})
    valid_nucleotides = args[:against]

    chain.each_char do |n|
      unless valid_nucleotides.include?(n)
        raise ArgumentError, "Not DNA nucleotide"        
      end
    end
  end
end

class NucleotideBreakdown
  def self.from(nucleotides)
    new(nucleotides)
  end
  
  def initialize(nucleotides)
    @nucleotides = nucleotides
    @datastore = NucleotideStore.new
    count
  end

  def by_nucleotide
    @datastore
  end

  def number_of(nucleotide)
    @datastore[nucleotide]
  end

  private

  def count
    each_nucleotide do |n|
      @datastore[n] += 1
    end
  end

  def each_nucleotide
    @nucleotides.each_char do |n|
      yield n
    end
  end
end

class NucleotideStore < Hash
  def self.empty_dna_hash
    @empty_dna_hash ||= 
      DNA::DNA_NUCLEOTIDES.each_with_object({}) { |n, h| h[n] = 0 }
  end
  
  def initialize
    super(0).tap do |h|
      h.merge!(self.class.empty_dna_hash)
    end
  end

end