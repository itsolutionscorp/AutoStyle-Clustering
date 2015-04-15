class Hamming

  attr_reader :st1, :st2

  def self.setup_strands(dna1,dna2)
    @st1= dna1.chars.to_enum
    @st2= dna2.chars.to_enum
  end

  def self.compute(dna_strand1,dna_strand2)
    self.setup_strands(dna_strand1,dna_strand2)
    hamming_distance = 0
    loop{ hamming_distance += 1 unless @st1.next == @st2.next}
    hamming_distance
  end
   
end
  
