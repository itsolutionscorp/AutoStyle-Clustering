#!/usr/bin/env ruby -w
# encoding: UTF-8

class Raindrops

  DICTIONARY = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def convert(n)
    factors = DICTIONARY.select do |k, _|
      n % k == 0
    end

    factors.empty? ? String(n) : factors.values.join
  end

end
