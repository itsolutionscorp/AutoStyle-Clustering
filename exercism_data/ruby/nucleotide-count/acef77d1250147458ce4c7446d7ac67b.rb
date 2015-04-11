#!/usr/bin/ruby
# Nucleotide Count Example

class DNA
  NUCS = ['A', 'G', 'C', 'T', 'U'].sort
  DNUCS = ['A', 'G', 'C', 'T'].sort
  
  def initialize(string)
    raise ArgumentError unless valid?(string, DNUCS)
    @string = Array string.chars
  end
  
  def valid?(s,nucs)
    (Array s.chars).uniq.each do |c|
      return false unless nucs.include?(c)
    end
    true
  end
  
  def count(nuc)
    raise ArgumentError unless valid?(nuc, NUCS)
    @string.count(nuc)
  end
  
  def nucleotide_counts
    h = DNUCS.each_with_object({}) do | n, h |
      h[n] = count(n)
    end
  end
end
