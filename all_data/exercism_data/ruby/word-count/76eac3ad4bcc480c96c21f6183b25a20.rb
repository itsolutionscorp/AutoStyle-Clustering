class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    counts = {}
    unique_words.each do |word|
      counts[word] = words.count(word)
    end
    counts
  end

  def words
    @words ||= @text.gsub(/[^\w]/, ' ').downcase.split
  end

  def unique_words
    words.uniq
  end
end
