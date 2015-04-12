class Hamming
  class << self
    def compute(s1, s2)
      s1.chars.zip(s2.chars).inject(0) do |sum, (x, y)|
        if y && x != y
          sum + 1
        else
          sum
        end
      end
    end
  end
end
