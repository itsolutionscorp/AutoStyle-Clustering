class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words # note: no parameter
    @phrase.downcase.split(/\W+/)
  end

end
