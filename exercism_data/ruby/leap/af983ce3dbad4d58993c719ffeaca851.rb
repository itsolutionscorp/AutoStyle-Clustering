#!/usr/bin/env ruby
# encoding: utf-8
# begin
# A year es bisiesto si es divisible por cuatro,
# excepto los principios de year (los divisibles por 100),
# que para ser bisiestos deben de ser divisibles tambien por 400.
# end
class Year
  def self.leap?(anio)
    return true if anio % 4 == 0  && anio % 100 != 0 || anio % 400 == 0
    # return true if ((anio % 4 == 0 ) && (anio % 100 != 0 || anio % 400 == 0))
  end
end
