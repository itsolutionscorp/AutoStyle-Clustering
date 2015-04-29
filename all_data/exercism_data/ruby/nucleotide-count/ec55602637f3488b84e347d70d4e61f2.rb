class DNA
  attr_reader :nucleotide_counts

  def initialize(n)
    @nucleotide_counts = { "A" => 0, "C" => 0, "G" => 0, "T" => 0}
    n.split(//).map do |n| 
      check_key(n)
      @nucleotide_counts[n] += 1
    end
  end

  def count(n)
    check_key(n)
    @nucleotide_counts[n]
  end

  private

  def check_key(n)
    raise ArgumentError unless @nucleotide_counts.keys.include? n
  end
end
