class Hamming

  attr_reader   :strand1, :strand2
  attr_accessor :strand

  def initialize strand1, strand2
    @strand1 = strand1[0...reduce_strands(strand1,strand2)]
    @strand2 = strand2[0...reduce_strands(strand1,strand2)]
    @strand  = ""
  end

  def self.compute strand1, strand2
    dna_calc = new(strand1,strand2)
    dna_calc.distance 
  end

  def distance 
    strand_distance 
    strand.size
  end

  private

  def reduce_strands strand1, strand2
    strands = [strand1, strand2]
    strands.min{ |strand, other| strand.length <=> other.length }.length
  end
  
  def strand_distance 
    strand1.split(//).collect.with_index do |base, index|
      strand << base if base != strand2[index]
    end
  end

end
