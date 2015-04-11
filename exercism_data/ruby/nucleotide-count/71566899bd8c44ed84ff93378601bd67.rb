class DNA
  attr_reader :strand

  def initialize(strand)
    validate_strand strand

    @strand = strand
  end

  def count(nucleotide)
    validate nucleotide

    @count ||= make_counts
    @count[nucleotide]
  end

  def nucleotide_counts
    @count ||= make_counts
  end

  private

  def validate_strand(strand)
    strand.chars.each do |nucleotide|
      validate nucleotide
    end
  end

  def validate(nucleotide)
    raise ArgumentError unless empty_strand.keys.include? nucleotide
  end

  def make_counts
    @strand.chars.reduce(empty_strand) do |counts, nucleotide|
      counts[nucleotide] += 1
      counts
    end
  end

  def empty_strand
    {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
  end

end
