class Phrase

  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words_array.reduce( Hash.new 0 ) do |hash, word|
      hash[ word ] += 1
      hash
    end
  end

private

  def words_array
    phrase.downcase.scan(/\w+[']?\w?/)
  end

end
