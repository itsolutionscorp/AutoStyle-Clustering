class DNA
  attr_reader :nucleotides
  DNA_NT = %w(A T C G)
  ALL_NT = DNA_NT + ['U']

  def initialize(sequence)
    @nucleotides = Hash.new(0)
    DNA_NT.each{|nt| @nucleotides[nt] = 0 }
    process_nucleotides(sequence)
  end
  
  def count(amino)
    raise ArgumentError unless ALL_NT.include?(amino) 
    nucleotides[amino]
  end
  
  def nucleotide_counts
    nucleotides
  end

  private
  
  def process_nucleotides(sequence)
    sequence.chars.each{|nt| nucleotides[nt] += 1 }
  end
end
