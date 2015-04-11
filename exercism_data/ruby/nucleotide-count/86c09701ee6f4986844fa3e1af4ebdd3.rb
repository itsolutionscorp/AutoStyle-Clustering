require 'set'

class DNA
  Components = Set.new ['A', 'T', 'C', 'G']
  Nucleotides = Components + ['U']

  def initialize(strand)
    @strand = strand.chars
    @strand.each do |n|
      unless Components.member? n
        raise ArgumentError.new("invalid nucleotide")
      end
    end
  end

  def count(nucleotide)
    if Nucleotides.member? nucleotide 
      @strand.count nucleotide
    else
      raise ArgumentError.new("invalid nucleotide")
    end
  end

  def nucleotide_counts
    counts = Hash[Components.map {|n| [n, 0]}]
    @strand.each do |n|
      counts[n] += 1
    end
    counts
  end
end
