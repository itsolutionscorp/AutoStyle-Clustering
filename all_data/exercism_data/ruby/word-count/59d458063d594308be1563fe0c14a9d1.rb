class Phrase
  def initialize(phrase)
    @phrase = phrase  
  end

  def word_count
    words.inject(Hash.new(0)) { |h,i| h[i] += 1; h }
  end

  def words
    words = @phrase.downcase.scan(/\w+'?\w+|\d+/)
  end
end
