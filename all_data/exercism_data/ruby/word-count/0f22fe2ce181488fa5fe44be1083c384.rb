class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list = Hash.new(0)
    get_word { |word| word_list[word] += 1 }
    word_list
  end

  private

  attr_reader :phrase

  def get_word
    phrase.downcase.scan(/[\w]+/) { |word| yield word }
  end
end
