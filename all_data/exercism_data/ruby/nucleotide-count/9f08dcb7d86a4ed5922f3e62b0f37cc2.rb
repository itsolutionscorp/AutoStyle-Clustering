class DNA
  NUCLEOTIDE_COUNT = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

  def initialize(sequence)
    @sequence = sequence
    valid_dna_check(@sequence)
  end

  def count(nucleotide)
    valid_dna_check(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    NUCLEOTIDE_COUNT.each {|k,_| NUCLEOTIDE_COUNT[k] = count(k)}
  end

  private
  def valid_dna_check(sequence)
    sequence.each_char do |nucleotide|
      raise ArgumentError if NUCLEOTIDE_COUNT[nucleotide].nil?
    end
  end
end
