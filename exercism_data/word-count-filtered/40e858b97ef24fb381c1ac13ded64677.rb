class Phrase
  attr_reader :word_list

  def initialize(word)
    @word_list = word.downcase.scan(/[\w']+/)
  end

  def word_count
    count = Hash.new(0)
    word_list.each { |word| count[word] += 1}
    count
  end

end
