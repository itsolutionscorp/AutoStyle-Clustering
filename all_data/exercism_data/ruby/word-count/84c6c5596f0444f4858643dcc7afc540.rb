class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  def words
    @text.downcase.split(/[^a-zA-Z0-9]+/)
  end
end
