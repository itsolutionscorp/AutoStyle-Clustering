class Phrase

  @phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, words|
      words[word] += 1
    end
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

end
