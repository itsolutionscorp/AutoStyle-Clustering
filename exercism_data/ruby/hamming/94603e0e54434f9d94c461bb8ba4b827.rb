class Hamming
  def self.compute(s1, s2)
    merge(s1, s2).count { |v1, v2| v1 != v2 }
  end

  private
    def self.merge(s1, s2)
      s1.chars.zip(s2.chars)
    end
end
