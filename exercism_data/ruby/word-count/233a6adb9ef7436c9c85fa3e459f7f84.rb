class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    count_each_of(words_in_the_phrase)
  end

  private

  def count_each_of(words)
    words.inject(Hash.new(0)) { |counts,word| counts[word] += 1; counts } 
  end

  def words_in_the_phrase
    @phrase.downcase.split(/\W+/)
  end
end
