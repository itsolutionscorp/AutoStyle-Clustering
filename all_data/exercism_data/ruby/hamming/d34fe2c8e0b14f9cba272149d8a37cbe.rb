module Hamming
  def self.compute(s1, s2)
    couples = s1.chars.take(s2.size).zip(s2.chars)
    couples.count { |(n1, n2)| n1 != n2 }
  end
end
