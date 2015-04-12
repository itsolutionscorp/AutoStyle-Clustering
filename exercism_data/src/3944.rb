class Hamming
  class << self
    def compute(s1, s2)
      s1.chars.zip(s2.chars).count do |x, y|
        y && x != y
      end
    end
  end
end
