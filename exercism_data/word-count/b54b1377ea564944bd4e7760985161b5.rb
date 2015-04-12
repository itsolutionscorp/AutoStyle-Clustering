class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @words ||= phrase.downcase.gsub(/[^\w']/, " ").split(" ")
  end

  def word_count
    @word_count ||= Hash[words.map do |word|
      [word, words.count(word)]
    end]
  end
end
