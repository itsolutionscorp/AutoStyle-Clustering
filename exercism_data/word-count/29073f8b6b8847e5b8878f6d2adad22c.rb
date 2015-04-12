#!/usr/bin/env ruby


class Phrase
  def initialize(s)
    @s = s
  end

  def word_count
    get_words.each_with_object(Hash.new(0)) { |w, m| m[w] = m[w] + 1}
  end

  def get_words
    @s.downcase.scan(/\b[\w']+\b/)
  end
end
