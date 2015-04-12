class Hamming
  def compute ( dna1, dna2 )
    hamming = 0
    length = [dna1.length, dna2.length].min 
    for i in 0..length-1
      hamming += 1 if dna1[i] != dna2[i]
    end
    hamming
  end
end
