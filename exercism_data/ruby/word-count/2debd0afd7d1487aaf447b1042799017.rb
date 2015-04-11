class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count  = Hash.new(0)
    
    words.each { |word| count[word] += 1 }

    count
  end

  private
  def words
    clean_phrase.split
  end

  def clean_phrase
    phrase.downcase.gsub(/[^\w\s]/, ' ')
  end
end
