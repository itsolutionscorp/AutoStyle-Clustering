class DNA

  def initialize(sequence)
    @bases = sequence.chars
  end

  def hamming_distance(comparison_sequence)
    count_mutations(comparison_sequence.chars)
  end

  private

    def count_mutations(comparison_bases)
      mutations = 0
      @bases.each do |base|
        return mutations if comparison_bases.empty?
        mutations += 1 unless base == comparison_bases.slice!(0)
      end
      mutations
    end

end
