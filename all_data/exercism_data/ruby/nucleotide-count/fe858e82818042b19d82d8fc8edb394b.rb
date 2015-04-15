class DNA

INVALID_DNA = Regexp.new(/[^ACGTacgt]/)
INVALID_NUCLEOTIDE = Regexp.new(/[^ACGTUuacgt]/)

attr_accessor :nucleotide

  def initialize(nucleotide)
    raise ArgumentError, 'DNA is not valid' if nucleotide =~ INVALID_DNA
    @nucleotide=nucleotide.split("")
  end

def count(letter)
  raise ArgumentError, "Nucleotide is not valid" if letter =~ INVALID_NUCLEOTIDE
  return 0 if nucleotide.nil?
  count = 0
  nucleotide.each do |item|
     count +=1 if item == letter
  end
  count
end

def nucleotide_counts
  nucleotide_counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
  nucleotide_counts["A"] = count("A")
  nucleotide_counts["T"] = count("T")
  nucleotide_counts["C"] = count("C")
  nucleotide_counts["G"] = count("G")
  nucleotide_counts
end

end
