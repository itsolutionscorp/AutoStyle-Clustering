class DNA
  def initialize(strand)
    @strand = strand
    @valid_nucleotides = { "A" => 0, "T" => 0, "C" => 0, "G" => 0 }
    @counts = { "A" => 0, "T" => 0, "C" => 0, "G" => 0 }

    _validate_strand strand
    _count_nucleotides
  end

  def count(nucleotide)
    _validate_strand nucleotide
    @counts[nucleotide]
  end

  def nucleotide_counts
    @counts
  end

  private

  def _count_nucleotides
    @strand.split('').each do |nucleotide|
      @counts[nucleotide] += 1
    end
  end

  def _validate_strand(strand)
    strand.split('').each do |nucleotide|
      raise ArgumentError if not @valid_nucleotides.key? nucleotide
    end
  end

end
