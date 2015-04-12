class Phrase

  def initialize(phrase)
    @phrase = phrase
    @words = @phrase.gsub(',',' ').gsub(/[^A-Za-z0-9' ]/, '').downcase.split(' ')
  end

  def word_count
    word_count = Hash.new(0)
    @words.each { |word| word_count[word] += 1 }
    return word_count
  end

end
