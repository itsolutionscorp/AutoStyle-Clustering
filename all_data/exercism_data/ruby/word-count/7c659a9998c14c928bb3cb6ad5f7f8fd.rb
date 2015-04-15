class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    {}.tap do |word_count|
      words.each do |word|
        word_count[word] = occurences(word)
      end
    end
  end

  private

  def words
    @words ||= @phrase.downcase.scan(/[a-z0-9]+/)
  end

  def occurences word
    words.count(word)
  end
end
