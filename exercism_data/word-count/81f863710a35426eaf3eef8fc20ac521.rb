class Phrase

  attr_reader :sentence, :words

  def initialize(sentence)
    @sentence = normalize(sentence)
    @words = @sentence.split
  end

  def normalize(sentence)
    sentence.strip.downcase.gsub(/[^a-z0-9 ]/, '')
  end

  def word_count

    word_occurences = {}

    words.uniq.each do |word|
      word_occurences[word] = words.count(word)
    end

    word_occurences
  end

end
