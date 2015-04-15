require 'pry-byebug'
class Nucleotide
  def self.from_dna strand
    raise ArgumentError if invalid? strand
    self.new strand
  end

  def self.invalid? strand
    !!strand.match(/[^ATGC]/)
  end

  def initialize strand
    @strand = strand
  end

  def count n
    @strand.count n
  end

  def histogram
    hist = {}
    ['A', 'C', 'T', 'G'].each { |n| hist[n] = 0 }
    @strand.chars.each { |n| hist[n] += 1 }
    hist
  end
end
