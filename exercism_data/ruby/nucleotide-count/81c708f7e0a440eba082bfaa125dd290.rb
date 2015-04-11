class Nucleotide
  def Nucleotide.from_dna (str)
    raise ArgumentError if /[^ATGCatgc]/ =~ str
    Nucleotide.new(str.upcase)
  end
  def initialize (str)
    @str = str
  end
  def count (c)
    @str.count(c) 
  end
  def histogram
    ["A", "T", "G", "C"].reduce({}){|d, c| d[c] = count(c); d}
  end
end
