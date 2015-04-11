class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select{|word| Word.new(word).letters == @word.letters}
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def letters
    @word.split("").each_with_object(Hash.new(0)){|letter, letters| letters[letter] += 1}
  end
end
