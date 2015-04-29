class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list.each_with_object(h = Hash.new(0)) { |word| h[word] += 1 }
  end

  def word_list
    phrase.downcase.split(/\W+/)
  end
end
