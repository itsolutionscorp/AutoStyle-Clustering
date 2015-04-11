class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase = {}
    @phrase.scan(/'?\b[0-9A-Za-z']+\b'?/).each { |word|
    if word.empty? != true
      if phrase[word.downcase] != nil
        phrase[word.downcase] += 1
      else
        phrase[word.downcase] = 1
      end
    end
    }
    phrase
  end
end
