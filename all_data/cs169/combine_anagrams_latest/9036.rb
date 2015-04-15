class String
  def sort
    split('').sort
  end

  def is_anagram?(other)
    self.downcase.sort == other.downcase.sort
  end
end

def combine_anagrams(words)
  output = []
  until words.empty? do
    anagrams = words.select { |w| words.first.is_anagram? w }
    words -= anagrams
    output << anagrams
  end
  output
end
