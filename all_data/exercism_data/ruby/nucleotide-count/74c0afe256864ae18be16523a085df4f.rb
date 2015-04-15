class DNA
  attr_reader :sequence, :nucleotide_counts

  def initialize(sequence)
    @nucleotide_counts = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    process(sequence)
    @sequence = sequence
  end

  def count(args)
    return @nucleotide_counts unless args
    if args =~ /^[ATCG]$/
      @nucleotide_counts[args]
    elsif args == 'U'
      0
    else
      raise ArgumentError
    end
  end

  private

  def process(sequence)
    if valid_dna?(sequence)
      sequence.each_char { |char| @nucleotide_counts[char] += 1 }
    else
      raise ArgumentError
    end
  end

  def valid_dna?(sequence)
    sequence =~ /\A[ATCG]*\z/
  end

end
