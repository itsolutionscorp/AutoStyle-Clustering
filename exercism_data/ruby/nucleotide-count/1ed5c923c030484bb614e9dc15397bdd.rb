class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.split("")
    ensure_valid_DNA
  end

  def count(nucleotide)
    ensure_valid_nucleotide(nucleotide)
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    tallies = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    strand.each_with_object(tallies) { |nucleotide, count| count[nucleotide] += 1; hash }
  end

  def ensure_valid_nucleotide(nucleotide)
    valid_nucleotides = ["A","T","G","C","U"]
    raise ArgumentError unless valid_nucleotides.include?(nucleotide)
  end

  def ensure_valid_DNA
    valid_DNA = ["A", "C", "G", "T"]
    strand.each do |letter|
      raise ArgumentError unless valid_DNA.include?(letter)
    end
  end

end
