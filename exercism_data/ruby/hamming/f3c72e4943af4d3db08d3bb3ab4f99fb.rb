class Hamming
  def self.compute(strand_one, strand_two)
    d = 0 
    strand_one.chars.each_with_index do |s1, i|
      if s1 != strand_two[i]
        d += 1
      end
    end
    d
  end
end
