class DNA
  DNA_NUCLEOTIDES               = %w( A T C G )
  NUCLEOTIDES = DNA_NUCLEOTIDES + %w( U )

  def initialize string
    raise ArgumentError unless dna_nucleotides?(string)
    @strand = string.chars.to_a
  end

  def count letter
    raise ArgumentError unless nucleotides?(letter)
    strand.select { |n| n == letter }.length
  end

  def nucleotide_counts
    strand.each_with_object(dna_counter) do |n, counter|
      counter[n] += 1
    end
  end

  def dna_counter
    DNA_NUCLEOTIDES.each_with_object({}) { |n, ctr| ctr[n] = 0 }
  end

  private

  attr_reader :strand

  def nucleotides? string
    check_against NUCLEOTIDES, string
  end

  def dna_nucleotides? string
    check_against DNA_NUCLEOTIDES, string
  end

  def check_against allowed, string
    string =~ /\A[#{allowed.join("")}]*\z/
  end

end
