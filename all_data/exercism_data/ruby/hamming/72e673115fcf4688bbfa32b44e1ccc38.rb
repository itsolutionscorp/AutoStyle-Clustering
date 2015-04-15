# dna1 = "GAGCCTACTAACGGGAT"
# dna2 = "CATCGTAATGACGGCCT"

class Hamming
  def self.compute(dna1, dna2)
    dna1 = dna1.split("")
    dna2 = dna2.split("")
    if dna1.length > dna2.length
      length = dna2.length
    else
      length = dna1.length
    end

    array1 = []
    array2 = []
    i = 0
    hamming_dist = 0
  
    while i < length #0<2
      dna1.each do |letter1|
        array1 << letter1
      end

      dna2.each do |letter2|
        array2 << letter2
      end
      if array1[i] != array2[i]
        hamming_dist += 1
      end

      i +=1
    end

    return hamming_dist

  end
end
