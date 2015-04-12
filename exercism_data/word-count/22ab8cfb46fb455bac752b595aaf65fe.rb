require 'pry'

class Phrase

  attr_reader :words, :new_words, :counts

  def initialize(words)
    @words     = words
    @new_words = new_words
  end

  def word_count
    word_cleaner
    counter
    @counts
  end

  def counter
    @counts = Hash.new(0)
    @new_words.each { |word| counts[word] += 1 }
    @counts
  end

  def word_cleaner
    clean_words = words.downcase.scan(/[\w']+/)
    @new_words  = clean_words.reject(&:empty?)
  end

end
