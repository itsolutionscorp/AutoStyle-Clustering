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

  private

  def lowercase_text
    @text.downcase
  end

  def remove_punctuation(string)
    string.gsub(/[^\w]/, ' ')
  end

  def words
    @words ||= remove_punctuation(lowercase_text).split
  end

  def unique_words
    words.uniq
  end
end
