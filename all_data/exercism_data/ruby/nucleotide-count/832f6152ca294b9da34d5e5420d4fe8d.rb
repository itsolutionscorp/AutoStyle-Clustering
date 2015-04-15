class DNA
  attr_accessor :nucleotide_counts
  def initialize(nucleotides)
    @nucleotide_counts = nucleotides.chars.each_with_object({'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}){
      |nucleotide, nucleotide_counts|
      nucleotide_counts[nucleotide] += 1 if nucleotide_counts[nucleotide]
    }
  end
  
  def count(nucleotide)
    if @nucleotide_counts[nucleotide]
      @nucleotide_counts[nucleotide]
    elsif nucleotide == 'U'
      0
    else
      raise ArgumentError
    end
  end
end
