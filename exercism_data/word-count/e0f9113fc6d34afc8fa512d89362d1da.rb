class Phrase
  attr_reader :sentence, :word_list

  def initialize(sentence)
    @sentence = sentence
    @word_list = prepare_word_list
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  private

  def prepare_word_list
    sentence.downcase.scan(/\w+/)
  end
end
