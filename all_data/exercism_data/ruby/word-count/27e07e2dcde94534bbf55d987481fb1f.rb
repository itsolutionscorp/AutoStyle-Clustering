class Phrase
  attr_accessor :word_count

  def initialize(phrase)
    words = word_array(phrase)
    instances_of_each_word(words)
  end

  private

  def word_array(phrase)
    words = phrase.split(/\W+/)
  end

  def instances_of_each_word(words)
    @word_count = Hash.new {0}
    words.each do |word|
      downcase_word = word.downcase
      @word_count[downcase_word] += 1
    end
  end

end
