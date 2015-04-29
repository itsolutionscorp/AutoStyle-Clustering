#!/usr/bin/env ruby

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |e, a|
      a[e.downcase] += 1
    end
  end

  private

  def words
    @phrase.split(/[^[:alnum:]']/).reject(&:empty?)
  end
end
