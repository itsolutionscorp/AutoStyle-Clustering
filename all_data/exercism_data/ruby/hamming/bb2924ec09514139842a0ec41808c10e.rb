class Hamming
  def self.compute(a,b)
    if not self.dna_len_equal?(a,b)
      puts "Sequences should be of the same length"
      return nil
    end
    d = 0
    a.chars.zip(b.chars).each do |c1,c2|
      d +=1 if c1 != c2
    end
    return d
  end

  def self.dna_len_equal?(a,b)
    return a.length == b.length
  end
end
