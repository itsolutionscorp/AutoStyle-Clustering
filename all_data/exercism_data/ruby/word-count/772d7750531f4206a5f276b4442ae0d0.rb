class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    result = {}
    words_in_phrase.each do |word|
      result[word] = count_of(word)
    end
    result
  end

  private

  def count_of(word)
    words_in_phrase.count(word)
  end

  def words_in_phrase
    @words ||= phrase_without_puctuation.split
  end

  def phrase_without_puctuation
    @phrase.gsub(/[^a-z0-9 ]/,' ')
  end
end
