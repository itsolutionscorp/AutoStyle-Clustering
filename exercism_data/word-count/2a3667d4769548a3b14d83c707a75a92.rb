class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence.downcase
  end

  def words
    sentence.scan(/[a-zA-Z0-9']+/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, result|
      result[word] += 1
    end
  end
end
