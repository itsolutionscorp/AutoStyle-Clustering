class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new { 0 }
    words.each do |w|
      counts[w] += 1
    end

    counts
  end

  private
  
  def words
    normalized_phrase.split
  end

  def normalized_phrase
    phrase.downcase.gsub /[^A-Z\d']/i, ' '
  end
end
