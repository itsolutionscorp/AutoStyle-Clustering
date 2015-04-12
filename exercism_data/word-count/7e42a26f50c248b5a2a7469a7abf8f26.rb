class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object({}) do |word, word_counts|
      word_counts[word] = 0 if word_counts[word].nil?
      word_counts[word] += 1
    end
  end

  private

  def words
    sanitised_text.split(' ')
  end

  def sanitised_text
    @text.downcase.gsub(/[^a-z0-9]+/, ' ')
  end
end
