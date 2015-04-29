class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def count(char)
    unless (nucleotides | ['U']).include?(char)
      raise ArgumentError,
        "#{char} is not a valid DNA nucleotide. Try one of #{nucleotides}!"
    end

    sequence.count(char)
  end

  def nucleotide_counts
    nucleotides.each_with_object({}) { |n, counts| counts[n] = count(n) }
  end

  private
  attr_reader :sequence

  def nucleotides
    %w[A C T G]
  end
end
