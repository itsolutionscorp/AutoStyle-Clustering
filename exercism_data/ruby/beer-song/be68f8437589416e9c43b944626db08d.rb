class DNA
  def initialize sequence
    @sequence = sequence.split ''
  end

  def count nucleotide
    verify_nucleotide nucleotide
    sequence.count nucleotide
  end

  def nucleotide_counts
    Hash.new.tap do |hash|
      dna_identifiers.each { |letter| hash[letter] = count letter }
    end
  end

  private
  attr_reader :sequence

  def verify_nucleotide nucleotide
    raise ArgumentError unless identifiers.include? nucleotide
  end

  def identifiers
    %w[A T C G U]
  end
end
