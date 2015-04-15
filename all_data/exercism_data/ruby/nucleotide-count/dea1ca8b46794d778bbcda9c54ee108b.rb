class DNA
  attr_reader :nucleotides

  VALID_NUCLEOTIDES = ["G", "C", "A", "T", "U"]

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    answer = Hash.new(0)
    ["G","C","A","T"].each {|letter| answer[letter] = 0 }
    nucleotides.each_char {|nucleotide| answer[nucleotide] += 1}
    answer
  end
end
