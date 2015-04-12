class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    prepped_phrase.each do |word|
      result[word] += 1
    end
    result
  end

  def prepped_phrase
   @phrase.downcase.gsub(/[^\w\s\d\']/, ' ').split
  end
end
