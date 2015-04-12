class Phrase
  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    count = Hash.new(0)
    @words.each { |word| count[word] = count[word]+1}
    count
  end
end
