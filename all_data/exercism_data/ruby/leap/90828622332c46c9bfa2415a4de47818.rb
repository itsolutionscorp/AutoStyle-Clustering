# -*- coding: UTF-8 -*-
# Write a program that will take a year and report if it is a leap year.
class Year
  def self.leap?(year)
    return if year % 4 == 0 && year % 100 != 0 || year % 400 == 0
  end
end
