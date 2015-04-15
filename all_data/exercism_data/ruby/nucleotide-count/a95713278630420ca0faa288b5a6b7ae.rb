class Nucleotide
  attr_reader :dna_strand, :histogram
  class << self
    alias_method :from_dna, :new
  end

  def initialize strand
    raise ArgumentError unless strand.scan(/[^AGTC]/).empty?
    @dna_strand = strand
    @histogram = {'A'=>0, 'T'=>0, 'C'=>0, 'G'=>0}
    @histogram.keys.each do |key|
      histogram[key] = count key
    end
  end

  def count nucleotide
    dna_strand.scan(/[#{nucleotide}]/).join.length
  end
end
