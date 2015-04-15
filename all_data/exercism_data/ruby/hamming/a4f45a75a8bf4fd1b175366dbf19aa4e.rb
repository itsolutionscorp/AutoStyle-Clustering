class Hamming
  def self.compute(dna1, dna2)
    dif = 0

    dna1.chars.each_with_index do |v,i|
      if dna2.chars.at(i) != nil
        dif += 1 if !(v.eql?(dna2.chars.at(i)))
      end
    end

    dif 
  end
end
