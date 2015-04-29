class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = clean(phrase)
  end

  def word_count
    hash = {}
    phrase.split(' ').each do |word|
      word = word.downcase
      hash[word] ? hash[word] += 1 : hash[word] = 1
    end
    hash    
  end

  private
  def clean(phrase)
    phrase.gsub(',', ' ').tr('^0-9A-Za-z ', '')
  end

end 
