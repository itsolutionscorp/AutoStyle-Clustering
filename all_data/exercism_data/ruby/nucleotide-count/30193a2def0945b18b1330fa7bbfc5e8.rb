class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %W(U)

  def initialize(chain)
    assert_nucleotides(chain)
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

    chain.chars.uniq.each do |n|
      unless valid_nucleotides.include?(n)
        raise ArgumentError, "Not DNA nucleotide"        
      end
    end
  end
end

class NucleotideBreakdown < Hash
  def self.empty_dna_hash
    @empty_dna_hash ||= 
      DNA::DNA_NUCLEOTIDES.each_with_object({}) { |n, h| h[n] = 0 }
  end

  def self.from(nucleotides)
    new(nucleotides)
  end

  def initialize(nucleotides)
    super(0)
    merge!(self.class.empty_dna_hash)
    count(nucleotides)
  end

  def by_nucleotide
    self.dup
  end

  def number_of(nucleotide)
    self[nucleotide]
  end

  private

  def count(nucleotides)
    @nucleotide_counts ||= nucleotides.chars.each do |n|
      self[n] += 1
    end
  end
end
