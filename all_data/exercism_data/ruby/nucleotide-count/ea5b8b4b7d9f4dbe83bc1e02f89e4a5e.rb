class DNA

  attr_reader :sequence

  def initialize(nucleotides)
    @sequence = nucleotides.chars
    raise ArgumentError if sequence.any? {|elem| !valid? elem}
  end

  def count(nucleotide)
    raise ArgumentError unless valid?(nucleotide)
    sequence.count(nucleotide)
  end

  def valid?(nucleotide)
    valid_nucleotides.include? nucleotide
  end

  def nucleotide_counts
    valid_nucleotides.each_with_object({}) { |type, counts| counts[type] = count(type)}
  end

  def valid_nucleotides
    @valid_nucleotides ||= ['A', 'T', 'C', 'G']
  end

end
