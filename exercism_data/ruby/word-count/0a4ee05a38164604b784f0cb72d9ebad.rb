class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}
    words.each do |word|
      word.downcase!
      count[word] = count[word] && count[word] + 1 || 1
    end
    count
  end

  private

  def words
    @phrase.split /\W+/
  end

end
