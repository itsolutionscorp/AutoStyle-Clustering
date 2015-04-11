class DNA
  attr_reader :nucleotide_counts

  def initialize sequence
    @nucleotide_counts = {"A" => 0, "T" => 0, "C" => 0, "G" => 0}
    count_nucleotides sequence
  end

  def count nucleotide
    if !nucleotide? nucleotide
      raise ArgumentError, "That's not a nucleotide, silly!"
    elsif nucleotide_counts[nucleotide]
      nucleotide_counts[nucleotide]
    else
      0
    end
  end

  private

  def count_nucleotides sequence
    nucleotide_counts.each_key { |n| nucleotide_counts[n] = sequence.count(n) }
  end

  def nucleotide? possible_nucleotide
    "ATCGU".split("").include? possible_nucleotide
  end
end
