class DNA
  attr_reader :nucleotide

  def initialize(nucleotide)
    @nucleotide = nucleotide.split("")
  end

  def count(gcat)
    raise ArgumentError if gcat == "X"
    return 0 unless ["G","C","A","T"].include? gcat
    nucleotide_counts[gcat]
  end

  def nucleotide_counts
    answer = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    nucleotide.each do |tide|
      answer[tide] += 1
    end
    answer
  end
end
