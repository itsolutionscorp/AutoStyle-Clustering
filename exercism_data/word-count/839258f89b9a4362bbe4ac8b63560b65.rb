# encoding: utf-8

# Phase implementation for exercism word-count exercise
class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    # Note: I normally really don't like Hash's default option,
    # but in this case it actually works elegantly.
    words(@phrase).each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def words(phrase)
    phrase.split(/[^A-Za-z0-9']/)
      .delete_if(&:empty?)
      .map(&:downcase)
  end
end
