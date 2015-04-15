class Hamming
  class << self
    def compute(s1, s2)
      distances(s1, s2).inject(&:+)
    end

    private

    def distances(s1, s2)
      pairs(s1, s2).map { |pair| compare_pair(pair[0], pair[1]) }
    end

    def pairs(s1, s2)
      s1.chars.zip(s2.chars).reject {|pair| pair.include?(nil)}
    end

    def compare_pair(s1, s2)
      s1 == s2 ? 0 : 1
    end
  end
end
