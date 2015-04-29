class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def words
    @words ||= @sentence.downcase.split(/\W+/)
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, frequencies|
      frequencies[word] += 1
    end
  end
end
