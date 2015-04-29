class DNA
  attr_reader :nucleotide_counts

  def initialize(sequence)
    @sequence = sequence
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @keys = @nucleotide_counts.keys
    count_nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless valid?(nucleotide)
    @nucleotide_counts[nucleotide]
  end

  def valid?(n)
    @keys.include?(n)
  end

  private

    def count_nucleotides
      @sequence.each_char do |n| 
        raise ArgumentError unless valid?(n)
        @nucleotide_counts[n] += 1 
      end
    end
end
