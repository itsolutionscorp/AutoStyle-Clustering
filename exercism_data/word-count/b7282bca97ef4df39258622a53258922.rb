class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    split_words = @phrase.downcase.scan(/[\w']+/)

    split_words.each_with_object(Hash.new(0)) do  |word, words_frequency|
      words_frequency[word] += 1
    end
  end
end
