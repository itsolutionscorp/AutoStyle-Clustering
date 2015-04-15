class DNA
  attr_reader :nucleotides

  NUCLEOTIDES_WHITELIST = ["G", "C", "A", "T", "U"]

  def initialize(nucleotides)
    @nucleotides = nucleotides.split("")
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES_WHITELIST.include? nucleotide
    return 0 unless ["G","C","A","T"].include? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    answer = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    nucleotides.each do |nucleotide|
      answer[nucleotide] += 1
    end
    answer
  end
end
