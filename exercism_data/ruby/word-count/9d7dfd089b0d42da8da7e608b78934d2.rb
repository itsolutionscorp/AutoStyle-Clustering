class Phrase

  def initialize(phrase)
    @phrase = phrase
  end


  def word_count
    result = {}
    word_prep.each do |word|
      if result[word]
        result[word] += 1
      else
        result[word] = 1
      end
    end
    result
  end

  def word_prep
    @phrase.downcase.gsub(/[^\w\s\']/, " ").split
  end
end
