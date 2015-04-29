class Phrase
  def initialize(sentence)
    @words = split_sentence_into_words(sentence)
  end

  def word_count
    @words.inject(Hash.new(0)){|hash, word| hash[word] = hash[word] += 1; hash}
  end

  private
  
  def split_sentence_into_words(sentence)
    sentence.downcase.split(/\W+/)
  end
end
