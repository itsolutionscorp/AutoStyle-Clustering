class Phrase
  attr :phrase
  def initialize(phrase)
    @phrase = phrase
  end
  def word_count
    words = Hash.new(0)
    word_list.each do |word|
      words[word] += 1
    end
    return words
  end
  private
  def word_list
    @phrase.split(/\W+/).map(&:downcase)
  end
end
