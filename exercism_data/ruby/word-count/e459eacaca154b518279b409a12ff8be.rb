class Phrase
  def initialize(sentence)
    @words = split_sentence_into_words(sentence)
  end

  def word_count
    @words.each_with_object(Hash.new(0)){ |word, hash| hash[word] += 1 }
  end

  private
  
  def split_sentence_into_words(sentence)
    sentence.downcase.split(/\W+/)
  end
end
