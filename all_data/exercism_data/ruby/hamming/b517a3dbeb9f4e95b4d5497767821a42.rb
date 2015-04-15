require 'pry'
class Hamming
  def initialize strand1, strand2
    @strand1 = strand1
    @strand2 = strand2
    @strand  = ""
  end

  def self.compute strand1, strand2
    dna_calc = new(strand1,strand2)
    dna_calc.distance
  end

  def distance
    @strand1.size <= @strand2.size ? (strand_distance @strand1, @strand2) : (strand_distance @strand2, @strand1)
    @strand.size
  end


  private
  
  def strand_distance shorter_strand, longer_strand
    shorter_strand.split(//).collect.with_index do |base, index|
      base != longer_strand[index]? @strand << base : nil
    end
  end

end






    
   
  

    





    
