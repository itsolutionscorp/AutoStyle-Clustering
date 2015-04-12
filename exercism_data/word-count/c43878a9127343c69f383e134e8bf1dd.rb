class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def normalise
    @text.downcase
  end

  def split_words
    normalise.scan(/\w+/)
  end

  def word_count
    split_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
