class Anagram
  
  def initialize(word)
    @word = Word.new word
  end

  def match(words)
    candidates = map_to_words Array(words)
    anagrams = find_anagrams_in candidates 
    stringify(anagrams)
  end

  private
    
    attr_reader :word

    def map_to_words(words)
      words.map { |word| Word.new word }
    end

    def find_anagrams_in(candidates)
      candidates.select { |candidate| word.anagram_of? candidate }  
    end

    def stringify(words)
      words.map(&:to_s)
    end
end

class Word

  def initialize(word)
    @word = word.to_s
  end

  def anagram_of?(other_word)
    chars == other_word.chars
  end

  def chars
    word.downcase.chars.sort
  end

  def to_s
    word
  end

  private

    attr_reader :word

end
