class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject.downcase
  end

  def list_of_words(words)
    words.select do |word|
      word(word.downcase) == subject.chars.sort
    end
  end

  def word(word)
    word.chars.sort
  end

  def remove_identical(words)
    words.select {|word| word unless word.downcase == subject.downcase}
  end

  def match(words)
    anagrams     = list_of_words(words)
    remove_identical(anagrams)
  end
end
