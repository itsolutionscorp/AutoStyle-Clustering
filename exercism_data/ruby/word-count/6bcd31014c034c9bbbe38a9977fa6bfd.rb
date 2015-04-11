class Phrase
  def initialize(phrase)
    @phrase = remove_punctuation(phrase.downcase)
  end

  def word_count
    @words ||= count_words
  end


  private
    def count_words
      words = {}
      @phrase.split.each { |word|
        words[word] = 0 unless words.has_key?(word)
        words[word] += 1
      }
      words
    end

    def remove_punctuation(phrase)
      phrase.gsub(/[[:punct:]\$\^&&[^']]/, ' ')
    end
end
