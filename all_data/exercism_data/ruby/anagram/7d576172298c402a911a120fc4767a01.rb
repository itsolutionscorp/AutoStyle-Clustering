class Anagram
  def initialize(sentence)
    @sentence = sentence
  end

  def match(words)
    words.each_with_object(Array.new(0)) do |word, array|
      if check_words(word,@sentence) && downcase_word(word) != downcase_word(@sentence)
        array << word
      end
    end
  end

  private
  def downcase_word(word)
    word.downcase
  end

  def sort_word(word)
    downcase_word(word).chars.sort.join
  end

  def check_words(word1, word2)
    sort_word(word1) == sort_word(word2)
  end
end
