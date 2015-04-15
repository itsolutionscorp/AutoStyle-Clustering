#!/usr/bin/env ruby


class Phrase
  def initialize(s)
    @s = s
    word_count
  end

  def word_count
    m = {}
    m.default = 0
    @s.downcase.gsub(/[^a-z0-9'\s,]/, '').gsub(',', ' ').split.map {|w|
      m[w] = m[w]+1
    }
    return m
  end
end
