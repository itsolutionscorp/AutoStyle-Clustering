class Hamming
  def self.compute(a,b)
    counter = 0

    a.chars.zip(b.chars).each do |pair|
      counter += 1 unless pair[0] == pair[1]
    end
    
    counter
  end
end
