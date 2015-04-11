class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.each_with_object([]) do |word, matches|
      matches << word if @word.is_anagram_of?(word)
    end
  end

  private

  class Word
    def initialize(word)
      @word = word.downcase
    end

    def is_anagram_of?(word)
      @word != word && normalise(@word) == normalise(word)
    end

    private

    def normalise(word)
      word.chars.sort.join
    end
  end

end
