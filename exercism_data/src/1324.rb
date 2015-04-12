class Hamming

  def compute(dna_strand1,dna_strand2)
    hamming_distance = 0
    st1 = dna_strand1.chars.to_enum
    st2 = dna_strand2.chars.to_enum

    loop{ hamming_distance += 1 unless st1.next == st2.next}
    hamming_distance
  end
   
end
  
