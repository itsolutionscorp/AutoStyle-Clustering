module Enumerable
  def count_by(&block)
    return to_enum(:count_by) unless block_given?

    group_by(&block).each_with_object(Hash.new) do |(k,v), hash|
      hash[k] = v.count
    end
  end
end

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
    @words.count_by(&:to_s)
  end
end
