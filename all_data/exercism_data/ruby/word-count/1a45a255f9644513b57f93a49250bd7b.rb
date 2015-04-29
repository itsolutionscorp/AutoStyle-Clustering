class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
    @words = clean
  end

  def word_count
    hash = {}
    @words.each do |word|
      hash[word] ? hash[word] += 1 : hash[word] = 1
    end
    hash    
  end

  private
  def clean
    phrase.gsub(',', ' ').tr('^0-9A-Za-z ', '').downcase.split(' ')
  end

end 
