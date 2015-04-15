class NucleicAcid

  attr_accessor :nucleotide_counts

  NUCLEOTIDES = %W|A T C G U|

  def count(nucleotide)
    nucleotide?(nucleotide)
    @nucleotide_counts.has_key?(nucleotide) ? @nucleotide_counts[nucleotide] : 0
  end

  def nucleotide?(nucleotide)
    raise ArgumentError, 'Not a valid nucleotide' unless NUCLEOTIDES.include?(nucleotide)
  end

end

class DNA < NucleicAcid

  DNA_NUCLEOTIDES = %W|A T C G|  

  def initialize(sequence)
    @sequence = sequence
    @nucleotide_counts = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

    sequence.chars.each do |nucleotide| 
      dna_nucleotide?(nucleotide)
      @nucleotide_counts[nucleotide] += 1
    end
  end

  def dna_nucleotide?(nucleotide)
    raise ArgumentError, 'Not a valid DNA nucleotide' unless DNA_NUCLEOTIDES.include?(nucleotide)
  end

end

class RNA < NucleicAcid

  RNA_NUCLEOTIDES = %W|A U C G|

  def initialize(sequence)
    @sequence = sequence
    @nucleotide_counts = { 'A' => 0, 'U' => 0, 'C' => 0, 'G' => 0 }

    sequence.chars.each do |nucleotide| 
      rna_nucleotide?(nucleotide)
      @nucleotide_counts[nucleotide] += 1
    end
  end

  def rna_nucleotide?(nucleotide)
    raise ArgumentError, 'Not a valid RNA nucleotide' unless RNA_NUCLEOTIDES.include?(nucleotide)
  end

end
