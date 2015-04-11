class Hamming
  class << self
    def compute(s1, s2)
      s1.chars.zip(s2.chars).select do |(x, y)|
        y && x != y
      end.length
    end
  end
end
