#!/usr/bin/env ruby

module Roman
  @roman = { 1000 => 'M', 500 => 'D', 100 => 'C', 50 => 'L', 10 => 'X',
             5 => 'V', 1 => 'I' }
  def self.to_roman(num)
    @roman.reduce('') do |acc, (k, v)|
      reps = num / k
      curr = reps < 4 ? v * reps : v + @roman[k * 5]
      num -= k * reps

      acc + curr
    end.gsub('VIV', 'IX').gsub('LXL', 'XC').gsub('DCD', 'CM')
  end
end

class Fixnum
  def to_roman
    Roman.to_roman(self)
  end
end
