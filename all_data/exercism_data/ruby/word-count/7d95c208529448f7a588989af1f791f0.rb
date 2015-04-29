class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def word_count
    split_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[normalize(word)] += 1
    end
  end

  private

  def normalize(text)
    text.downcase
  end

  def split_words
    @text.scan(/\w+/)
  end
end
