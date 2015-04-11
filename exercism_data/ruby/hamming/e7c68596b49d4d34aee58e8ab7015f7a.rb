class Hamming

  def self.compute dna, dna2
    if dna.length <= dna2.length 
      short = dna
      long = dna2
    else
      short = dna2
      long = dna
    end

    index = 0
    hamming = 0

    short.each_char do |char|
      if char != long[index] then hamming += 1 end
      index += 1
    end

    hamming
  end
end
