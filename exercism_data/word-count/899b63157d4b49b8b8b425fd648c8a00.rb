class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    WordCounter.new(words).count
  end

  private
  def words
    @sentence.downcase.scan(/\p{Word}+/)
  end
end

class WordCounter
  def initialize(words)
    @words = words
  end

  def count
    @words.group_by(&:to_s).each_with_object(Hash.new){|(k,v), hash| hash[k] = v.count}
  end
end
