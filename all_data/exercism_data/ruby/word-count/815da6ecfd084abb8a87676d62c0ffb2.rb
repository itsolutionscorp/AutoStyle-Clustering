class Phrase

  attr_reader :words

  def initialize(sentence)
    @words = normalize(sentence).split
  end

  def normalize(sentence)
    sentence.strip.downcase.gsub(/[^a-z0-9 ]/, '')
  end

  def word_count

    words.uniq.each_with_object({}) do |word, hash|
      hash[word] = words.count(word)
    end

  end

end
