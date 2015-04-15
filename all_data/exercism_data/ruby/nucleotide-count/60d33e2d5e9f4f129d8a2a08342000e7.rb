class DNA
  def initialize(sequence)
    raise ArgumentError unless sequence =~ /\A[ACGT]*\z/
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless %w(A C G T U).include? nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts()
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}.merge( element_counts )

  end

  private

    def element_counts
      Hash[
        @sequence.chars.
        group_by { |char| char }.
        map { |char, chars| [char, chars.count]  }
      ]
    end
  
end
