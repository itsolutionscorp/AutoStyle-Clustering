class Phrase

  def initialize(text)
    @text = text.downcase
  end

  def word_count
    words.each_with_object(WordSet.new) do |word, set|
      set << word
    end.to_hash
  end

private
  def words
    @text.scan(/\w+/)
  end

end

class WordSet

  def initialize
    @words = Hash.new {|hash, key| hash[key] = 0 }
  end
  def <<(word)
    @words[word] += 1
  end
  def to_hash
    @words.dup
  end

end
