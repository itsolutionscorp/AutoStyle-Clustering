class DNA
  def initialize(seq)
    @sequence = seq
    @nucleotide_counts = nil

    raise ArgumentError unless valid_sequence?
  end

  def nucleotide_counts
    
    # Calculate only once.
    return @nucleotide_counts unless @nucleotide_counts.nil?

    @nucleotide_counts = { 'A' => 0, 'C' => 0, 'G' => 0, 'T' => 0 }

    @sequence.each_char do |nucleotide| 
      @nucleotide_counts[nucleotide] += 1
    end

    @nucleotide_counts

  end

  def count(nucleotide)
    return 0 if nucleotide == 'U'

    raise ArgumentError unless nucleotide_counts.has_key?(nucleotide)

    nucleotide_counts[nucleotide]
  end

  def valid_sequence?
    /^[ACGT]*$/.match(@sequence)
  end
  
  private :valid_sequence?
end
