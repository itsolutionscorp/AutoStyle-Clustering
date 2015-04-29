class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def normalized_words
    words.map(&:downcase)
  end

  def words
    @text.scan(/\w+/)
  end
end
