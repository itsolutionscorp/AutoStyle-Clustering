class DNA

  def initialize dna_string
    @nucleotide_counts = {"A" => 0, "T" => 0, "C" => 0, "G" => 0}
    count_nucleotides dna_string
  end

  def count nucleotide
    if !nucleotide? nucleotide
      raise ArgumentError, "That's not a nucleotide, silly!"
    elsif @nucleotide_counts[nucleotide]
      @nucleotide_counts[nucleotide]
    else
      0
    end
  end

  def nucleotide_counts
    @nucleotide_counts
  end

  private

  def count_nucleotides dna_string
    dna_string.chars.each { |n| @nucleotide_counts[n] += 1 }
  end

  def nucleotide? possible_nucleotide
    "ATCGU".split("").include? possible_nucleotide
  end
end
