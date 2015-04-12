class Hamming
  def compute(strand1, strand2)
    if strand1 == strand2
      0
    else
      strand1 = strand1.split("")
      strand2 = strand2.split("")

      index = 0
      hamming = 0
      
      strand1.each do |value|
        hamming += 1 if strand1[index] != strand2[index]
        index += 1
      end
      hamming
    end
  end
end
