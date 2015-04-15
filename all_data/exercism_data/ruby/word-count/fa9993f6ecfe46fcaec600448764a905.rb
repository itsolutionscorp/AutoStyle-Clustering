class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  private

  def words
    @text.downcase.scan(/[a-z0-9]+/)
  end
end
