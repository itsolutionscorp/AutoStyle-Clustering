class DNA
  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    count_hash = {}
    nucleotides.each { |n| count_hash[n] = count(n) }
    count_hash
  end

private

  def validate_nucleotide(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide" unless all_nucleotides.include?(nucleotide)
  end

  def all_nucleotides
    %w[A G C T U]
  end

  def nucleotides
    all_nucleotides - ["U"]
  end

end
