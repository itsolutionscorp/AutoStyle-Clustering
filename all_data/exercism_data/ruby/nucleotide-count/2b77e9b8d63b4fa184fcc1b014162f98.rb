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
    unless Nucleotides.member? nucleotide 
      raise ArgumentError.new("invalid nucleotide")
    end

    do_count(nucleotide)
  end

  def nucleotide_counts
    Hash[Components.map {|n| [n, do_count(n)]}]
  end

  private

  def do_count(nucleotide)
    @strand.count(nucleotide)
  end
end
