class Nucleotide

  def initialize dna
    @dna = dna
  end

  def self.from_dna dna
    raise ArgumentError if dna.index(/[^ATCG]/)
    Nucleotide.new dna
  end

  def count letter
    @dna.chars.count {|x| x == letter}
  end
  
  def histogram
    result = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    result.each_key {|key| result[key] = count key}
  end

end
