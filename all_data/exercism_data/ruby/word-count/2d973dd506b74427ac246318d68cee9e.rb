class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

private

  def word_list
    @phrase.downcase.split(/\W+/)
  end

end
