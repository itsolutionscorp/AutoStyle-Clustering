class Hamming

  def self.compute(s1, s2)
    mutations(s1.chars, s2.chars)
  end

  private

  def self.mutations(s1, s2)
    s1.zip(s2).count { |pair| mutation?(pair) }
  end

  def self.mutation?(pair)
    pair.at(1) != nil && pair.at(1) != pair.at(0)
  end
end
