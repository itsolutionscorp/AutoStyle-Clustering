class DNA

  attr_accessor :strand

  def initialize(strand)
    @strand = strand
    validate_strand
  end

  def count(nucleotide)
    raise ArgumentError unless 
      nucleotides_for_dna_and_rna.include?(nucleotide)
      
      strand.count(nucleotide)
  end

  def nucleotide_counts
    {"A"=>count("A"), 
     "T"=>count("T"), 
     "C"=>count("C"), 
     "G"=>count("G")}
  end

  private

  def nucleotides_for_dna
    %w(A T C G)
  end

  def nucleotides_for_dna_and_rna
    nucleotides_for_dna << "U"
  end

  def validate_strand
    strand.split("").each do |letter| 
      raise ArgumentError unless 
      nucleotides_for_dna.include?(letter)
    end
  end

end
