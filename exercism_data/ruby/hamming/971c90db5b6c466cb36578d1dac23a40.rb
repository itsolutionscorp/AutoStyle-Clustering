class Hamming

  def self.compute(str1, str2)
    count = 0
    str1.chars.map.with_index { |l, i| count += self.compare(l, str2[i]) }
    count
  end

  private

    def self.compare(a, b)
      a == b ? 0 : 1
    end    
end
