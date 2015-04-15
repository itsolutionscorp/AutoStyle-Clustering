class Anagram
  def initialize(target_word)
    @target_word = target_word
  end

  def match(words)
    words.find_all do |word| 
      is_anagram?(word) && is_different?(word)
    end
  end

  private
    def is_anagram?(word)
      sorted_characters(word) == sorted_characters(@target_word)
    end

    def sorted_characters(word)
      word.downcase.chars.sort
    end

    def is_different?(word)
      word.downcase != @target_word.downcase
    end
end
