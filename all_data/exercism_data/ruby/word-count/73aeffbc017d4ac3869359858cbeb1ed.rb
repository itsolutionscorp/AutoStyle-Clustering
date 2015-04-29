class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    phrase = @phrase.gsub(/\W+/," ").split
    count = {}
    phrase.uniq.each{ |word| count[word] = phrase.count(word)}
    return count
  end
end
