class Phrase
  def initialize (phrase)
    @phrase = phrase
  end

  def word_count
    words = {}

    extract_words.each do |word|
      unless words.has_key?(word)
        words[word] = 0
      end

      words[word] += 1
    end

    words
  end

  private

  def extract_words
    @phrase.scan(/[\w']+/).map(&:downcase)
  end
end
