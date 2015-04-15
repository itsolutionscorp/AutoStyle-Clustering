class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.inject({}) do |hsh, word|
      hsh[word] = (hsh[word] || 0) +1
      hsh
    end
  end

private

  def words
    @phrase.downcase.split(/[^(\w|')]+/)
  end

end
