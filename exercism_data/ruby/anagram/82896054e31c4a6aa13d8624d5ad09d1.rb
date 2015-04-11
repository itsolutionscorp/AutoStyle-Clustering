class Anagram
  def initialize(word)
    @word = Anagrammable.new(word)
  end

  def match(words)
    words
      .map{ |word| Anagrammable.new(word) }
      .select{|word| word.anagram_of? @word }
  end

  class Anagrammable < String
    def anagram_of?(other)
      (letters == other.letters) && (downcase != other.downcase)
    end

    def letters
      downcase.chars.sort
    end
  end
end
