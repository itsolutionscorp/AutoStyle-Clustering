class Phrase

  WORD_REGEX = /\b(?:\w+|\d+)\b/

  def initialize(sentence)
    @sentence = sentence
  end

  def words
    @sentence.scan(WORD_REGEX)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |counts, word|
      counts[word.downcase] += 1
    end
  end

end
