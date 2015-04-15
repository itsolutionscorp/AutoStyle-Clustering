class Phrase
  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
  end

  def word_count
    @words.uniq.inject({}) do |memo, word|
      memo.merge word => @words.count(word)
    end
  end
end
