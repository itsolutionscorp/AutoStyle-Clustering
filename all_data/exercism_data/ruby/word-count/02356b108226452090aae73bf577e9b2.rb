class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count = Hash.new {0}
    words.each do |word|
      word_count[word] += 1
    end
    word_count
  end

  def words
    @phrase.downcase.split(/\W+/)
  end

end
