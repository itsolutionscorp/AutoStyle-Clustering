class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def split_sentence
    @words ||= @sentence.downcase.split(/\W+/)
  end

  def word_count
    @word_count ||= split_sentence.each_with_object(Hash.new(0)) do |word, result|
      result[word] += 1
    end
  end
end
