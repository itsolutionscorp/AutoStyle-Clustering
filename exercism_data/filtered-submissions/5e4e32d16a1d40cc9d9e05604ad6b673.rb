class Hamming
  def compute(string1, string2)
    hamming = 0 
    dna1 = string1.split("")
    dna2 = string2.split("")

    dna1.each_with_index do |dna , index|
      if dna != dna2[index]
        hamming += 1
      end
    end
    return hamming
  end
end
