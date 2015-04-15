class DNA

  attr_reader :dna, :nucleotides
  
  def initialize dna
    @dna = dna
    @nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @uracil = "U"
  end

  def count  symbol
    0 if dna.empty?
    raise ArgumentError unless nucleotides.include?(symbol)  || @uracil == symbol
    dna.chars.count symbol
  end

  def nucleotide_counts
    nucleotides if dna.empty?
    nucleotides.each_with_object( Hash.new)  {  |nucleotide, result|  result[nucleotide.first]  = count(nucleotide.first) }
  end
end
