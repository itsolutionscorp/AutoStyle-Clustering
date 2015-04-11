class Anagram

  attr_reader :subject, :potential_anagrams

  def initialize(word)
    @subject = word.downcase
  end

  def match(test_words)
    @potential_anagrams = remove_duplicates test_words
    return_anagrams_or_nil
  end

  def return_anagrams_or_nil
    potential_anagrams.select {|word| word.downcase.chars.sort == subject.chars.sort}
  end

  def remove_duplicates(test_words)
    test_words.reject {|word| word.downcase == subject}
  end

end
