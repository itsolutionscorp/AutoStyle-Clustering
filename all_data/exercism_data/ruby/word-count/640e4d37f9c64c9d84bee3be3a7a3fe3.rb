# encoding: utf-8

class Phrase < Struct.new(:phrase)
  def words
    Words.from_phrase(phrase)
  end

  def word_count
    words.count_by_word
  end
end

class Words < Struct.new(:words)
  NOT_WORD = /[^\w]+/

  def self.from_phrase(phrase)
    new(split(phrase))
  end

  def count_by_word
    counter = WordCounter.new
    counter << words
    counter.statistics
  end

  private

  def self.split(phrase)
    phrase.split(NOT_WORD)
  end
end

class WordCounter
  def initialize
    @stats = Hash.new(0)
  end

  def add(*words)
    words.flatten!
    add_many words
  end
  alias_method :<<, :add

  def statistics
    @stats
  end

  private

  def add_many(words)
    words.each { |word| add_one word }
  end

  def add_one(word)
    @stats[word.downcase] += 1
  end
end
