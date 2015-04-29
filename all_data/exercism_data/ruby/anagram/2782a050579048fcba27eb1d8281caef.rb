class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word
  end

  def match(test_words)
    potential_anagrams = remove_duplicates test_words
    potential_anagrams.select {|word| char_hash(word) == char_hash(subject)}
  end

  def char_hash(word)
    char_hash = {}
    word.downcase.split('').each do |letter|
      char_hash[letter] ? char_hash[letter] += 1 : char_hash[letter] = 1
    end
    char_hash
  end

  def remove_duplicates(test_words)
    test_words.reject {|word| word.downcase == subject.downcase}
  end

end
