class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new {0}) do |word, hsh|
      hsh[word] += 1
    end
  end

  def words
    @phrase.downcase.scan(/[a-zA-Z0-9']+/)
  end

end