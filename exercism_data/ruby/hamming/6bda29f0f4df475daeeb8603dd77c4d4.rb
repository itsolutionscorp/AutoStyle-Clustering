class Hamming

  def self.compute(dna, copy)
    count = 0
    newdna = dna.split("")
    (0..dna.length-1).each do |index|
      if newdna[index] == copy[index]
      elsif newdna[index] != copy[index] && !copy[index].nil?
        count += 1
      end
    end
    count
  end

end
