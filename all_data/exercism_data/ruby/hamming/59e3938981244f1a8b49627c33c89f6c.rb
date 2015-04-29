class Hamming
  def self.compute(a,b)
    a.chars.zip(b.chars).reduce(0) do |distance,pair|
      pair[0] == pair[1] ? distance : distance + 1
    end
  end
end
