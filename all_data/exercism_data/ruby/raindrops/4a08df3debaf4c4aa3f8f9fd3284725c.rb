#!/usr/bin/env ruby -w
# encoding: UTF-8

class Raindrops

  DICTIONARY = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def convert(input_number)
    if (translated = prime_factors(input_number)).empty?
      String(input_number)
    else
      translated.values.join
    end
  end

  private

  def prime_factors(number)
    DICTIONARY.select { |prime, _| Integer(number) % prime == 0 }
  end

end
