class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words(@phrase).each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words(phrase)
    phrase.downcase.split(/\W+/)
  end
end
