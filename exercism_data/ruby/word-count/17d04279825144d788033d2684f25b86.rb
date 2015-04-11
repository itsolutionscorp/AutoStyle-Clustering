class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end

private

  def words
    @phrase.downcase.split(/[^(\w|')]+/)
  end

end
