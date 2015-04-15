class Hamming
  #def self.compute x, y
  #x.chars.zip(y.chars).reduce(0) { |memo, tuple| memo += (tuple[0] <=> tuple[1]).abs }
  #end

  def self.compute(s1,s2)
    c = 0
    (0..s1.length).each do |i|
      c += 1 unless s1[i] == s2[i]
    end
    c
  end

    dna_one.chars.zip(dna_two.chars).count { |c1, c2| c1 != c2 }

end
