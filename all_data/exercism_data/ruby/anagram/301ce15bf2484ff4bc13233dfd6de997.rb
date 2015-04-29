class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word.downcase
  end

  def match(test_words)
    potential_anagrams = test_words.uniq {|word| word.downcase}
    potential_anagrams.select {|potential_anagram| anagram?(potential_anagram)}
  end

  def anagram?(potential_anagram)
    if potential_anagram != subject
      potential_anagram.downcase.chars.sort == subject.chars.sort
    else
      false
    end
  end

end
