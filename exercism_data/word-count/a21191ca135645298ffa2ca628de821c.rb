class Phrase
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, word_frequencies|
      word_frequencies[word] += 1
    end
  end

  private

  WORD_CHARACTERS = /[\w']+/

  def words
    @sentence.downcase.scan(WORD_CHARACTERS)
  end
end
