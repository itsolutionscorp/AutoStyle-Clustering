class DNA

  def initialize(base_strand)
    @base_strand = base_strand
  end

  def hamming_distance(test_strand)
    unless @base_strand.nil? || test_strand.nil?
      mutations = @base_strand.chars.zip(test_strand.chars).count { | pair | mutation?(pair) }
    end
    mutations ||= 0
  end

  private

    def mutation?(pair)
      pair.first != pair.last unless pair.first.nil? || pair.last.nil?
    end
end
