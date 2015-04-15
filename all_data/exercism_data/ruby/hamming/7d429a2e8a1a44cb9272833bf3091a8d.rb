class Hamming
  def self.compute(str1, str2)
    find_differences(normalize(str1), normalize(str2)).size
  end

  def self.normalize(str)
    String(str).upcase
  end

  def self.pairs(str1, str2, &block)
    full_pairs = str1.chars.to_a.zip(str2.chars.to_a)
    full_pairs.select{|(c1, c2)| c1 && c2}
  end

  def self.find_differences(str1, str2)
    pairs(str1, str2).each_with_object([]) do |(chr1, chr2), a|
      a << [ chr1, chr2 ] unless chr1 == chr2
    end
  end
end
