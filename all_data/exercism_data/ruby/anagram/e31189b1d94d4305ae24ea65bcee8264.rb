class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word.downcase
  end

  def match(test_words)
    potential_anagrams = remove_duplicates test_words
    potential_anagrams.select do |potential_anagram|
      is_anagram?(potential_anagram)
    end
  end

  def is_anagram?(potential_anagram)
    potential_anagrams.downcase.chars.sort == subject.chars.sort
  end

  def remove_duplicates(test_words)
    test_words.reject {|word| word.downcase == subject}
  end

end
