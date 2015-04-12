class Phrase

  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase.gsub(/[^A-Za-z0-9]/, ' ')
  end

  def word_count
    words = phrase.split
    words.inject( Hash.new(0) ) do |hsh, word| 
      hsh[word.downcase] += 1
      hsh
    end
  end

end
