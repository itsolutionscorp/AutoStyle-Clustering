class Phrase
  attr_reader :word_count_result
  attr_reader :phrase

  def initialize(input)
    @phrase = input
  end

  def word_count
    temp = phrase.downcase.scan(/[\w+\d+\']+/)
    @word_count_result = Hash.new
    temp.each{|word| @word_count_result[word] = temp.count(word)}
    @word_count_result
  end
end
