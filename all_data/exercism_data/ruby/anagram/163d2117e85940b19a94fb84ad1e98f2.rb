class Anagram

  attr_reader :word_to_match

  def initialize(word)
    @word_to_match = word 
  end

  def match(array)
    array = remove_duplicates array
    array.select {|word| char_hash(word) == char_hash(word_to_match)}
  end

  def char_hash(word)
    letters = word.downcase.split('')
    char_hash = {}
    letters.each do |letter|
      char_hash[letter] ? char_hash[letter] += 1 : char_hash[letter] = 1
    end
    char_hash
  end

  def remove_duplicates(array)
    array.reject {|word| word.downcase == word_to_match.downcase}
  end

end
