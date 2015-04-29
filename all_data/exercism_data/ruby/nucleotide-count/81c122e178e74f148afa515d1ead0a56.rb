class DNA

  attr_accessor :strand

  def initialize(strand)
    @strand = strand
    strand.split("").each do |letter| 
    raise ArgumentError unless 
      accepted_nucleotides_for_dna.include?(letter)
    end
  end

  def count(nucleotide)
    raise ArgumentError unless 
      accepted_nucleotides_for_dna_and_rna.include?(nucleotide)
      
      strand.count(nucleotide)
  end

  def nucleotide_counts
    counts = {"A"=>count("A"), 
              "T"=>count("T"), 
              "C"=>count("C"), 
              "G"=>count("G")}
    counts
  end

  def accepted_nucleotides_for_dna
    %w(A T C G)
  end

  def accepted_nucleotides_for_dna_and_rna
    %w(A T C G U)
  end

end
