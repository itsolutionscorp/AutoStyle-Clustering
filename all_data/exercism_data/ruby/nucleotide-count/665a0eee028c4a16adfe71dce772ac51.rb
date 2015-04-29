class DNA
  
  DNA_NUCLEOTIDES = ["A", "C", "G", "T"]
  
  def initialize(dna)
    @dna = dna.upcase
    validate_dna
  end
  
  def count(nt)
    nt = nt.upcase
    validate_nucleotide nt
    @dna.count(nt)
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.reduce({}) do |counts, nt|
      counts[nt] = count(nt)
      counts
    end
  end
  
  private
  
  def validate_dna
    @dna.each_char do |nt|
      validate_nucleotide nt, dna: true
    end
  end
  
  def validate_nucleotide(nt, options = {})
    rna_nucleotide = nt == "U" && options[:dna].nil?
    raise ArgumentError unless DNA_NUCLEOTIDES.include?(nt) || rna_nucleotide
  end
  
end
