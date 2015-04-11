class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.strip
  end

  def word_count
    return words.each_with_object(Hash.new(0)) { |w, counts| counts[w] += 1 }
  end

  private
  def words
    return @phrase.scan(/[[:word:]]+/)
  end
end
