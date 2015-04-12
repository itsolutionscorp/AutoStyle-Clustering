class Phrase

  attr_reader :phrase
  private :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words
    phrase.downcase.scan(/\w+/)
  end
end
