class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = count words_in phrase
  end

  def count(words)
    words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end

  def words_in(phrase)
    phrase.downcase.scan(/\w+/)
  end
end
