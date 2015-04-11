class DNA
  attr_reader :strand

  def initialize(input)
    @strand = input.split(//)
  end

  def count(nucleotide)
    validate_nucleotide nucleotide
    strand.select { |n| n == nucleotide }.count
  end

  def nucleotide_counts
    nucleotides_in_dna.each_with_object(Hash.new(0)) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def nucleotides_in_dna
    %w( A T C G )
  end

  def valid_nucleotides
    nucleotides_in_dna + ['U']
  end

  def validate_nucleotide(nucleotide)
    unless valid_nucleotides.include?(nucleotide)
      raise ArgumentError.new("'#{ nucleotide }' is not a valid nucleotide")
    end
  end
end
