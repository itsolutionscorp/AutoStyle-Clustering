class Hamming
  def self.compute(strand1, strand2)
    short, long = [strand1, strand2].sort { |a, b| a.length <=> b.length }
    result = 0
    for i in 0...short.length
      short[i] == long[i] ? nil : result += 1
      i += 1
    end
    result
  end
end
