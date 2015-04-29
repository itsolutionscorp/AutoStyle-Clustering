class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}
    counts.default = 0
    word_list.each_with_object(counts) { |word| counts[word] += 1 }
  end

  def word_list
    phrase.downcase.split(/\W+/)
  end
end
