class Hamming

  def initialize(strand_a, strand_b)
    @short_strand, @long_strand = [strand_a, strand_b].map(&:chars).sort_by(&:length)
  end

  def self.compute(strand_a, strand_b)
    Hamming.new(strand_a, strand_b).distance
  end

  def distance
    @distance ||= mutations.inject(:+)
  end

  private

  def mutations
    genomes.map { |a, b| (a <=> b).abs }
  end

  def genomes
    @short_strand.zip @long_strand
  end

end
