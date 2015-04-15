class Hamming
  def self.compute(a,b)
    a.chars.zip(b.chars).inject(0)do |sum, obj|
      sum +=1 unless obj[0] == obj[1]
      sum
    end
  end
end
