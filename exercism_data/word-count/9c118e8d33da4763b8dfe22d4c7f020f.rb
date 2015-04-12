class Phrase
  def initialize (phrase)
    @phrase = phrase
  end

  def word_count
    words = Hash.new(0)

    extract_words.each do |word|
      words[word] += 1
    end

    words
  end

  private

  def extract_words
    @phrase.scan(/[\w']+/).map(&:downcase)
  end
end
