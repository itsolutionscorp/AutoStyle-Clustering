class Phrase

  attr_reader :sentence

  def initialize(sentence)
    @sentence = normalize(sentence)
  end

  def normalize(sentence)
    sentence.strip.downcase.gsub(/[^a-z0-9 ]/, '')
  end

  def word_count
    words = sentence.split
    word_occurences = {}

    words.each do |word|
      word_occurences[word] = words.count(word) unless word_occurences.include?(word)
    end

    word_occurences
  end

end
