# encoding: utf-8

# Phase implementation for exercism word-count exercise
class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words(@phrase).reduce({}) do |counts, word|
      counts[word] ||= 0
      counts[word] += 1
      counts
    end
  end

  private

  def words(phrase)
    phrase.split(/[^A-Za-z0-9']/)
      .delete_if(&:empty?)
      .map(&:downcase)
  end
end
