class Phrase

  def initialize(phrase)
    @words = phrase.downcase.gsub(/(\W)/, " ").split
  end

  def word_count
    Hash[@words.zip @words.map{|x| count_word(x)}]
  end

  private

  def count_word(word)
    @words.count(word)
  end
end
