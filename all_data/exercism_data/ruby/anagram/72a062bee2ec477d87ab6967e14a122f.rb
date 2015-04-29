class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word.downcase
  end

  def match(test_words)
    potential_anagrams = remove_case_insensitive_duplicate_words test_words
    potential_anagrams.select do |potential_anagram|
      anagram?(potential_anagram)
    end
  end

  def anagram?(potential_anagram)
    potential_anagram.downcase.chars.sort == subject.chars.sort
  end

  def remove_case_insensitive_duplicate_words(test_words)
    test_words.reject {|word| word.downcase == subject}
  end

end
