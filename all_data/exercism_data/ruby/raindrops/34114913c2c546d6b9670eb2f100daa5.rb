#!/usr/bin/env ruby -w
# encoding: UTF-8

class Raindrops

  DICTIONARY = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def convert(input)
    if has_factors?(input)
      translate(input)
    else
      String(input)
    end
  end

  private

  def has_factors?(number)
    DICTIONARY.any?(&factors_of?(number))
  end

  def translate(number)
    DICTIONARY.select(&factors_of?(number)).values.join
  end

  def factors_of?(number)
    ->(*factor) { Integer(number) % (factor.flatten[0]) == 0 }
  end

end
