require 'prime'
module Raindrops
  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    keys = Prime.prime_division(number).map(&:first).select { |n| [3, 5, 7].include?(n) }.uniq
    keys.any? ? keys.map { |key| RAINDROPS[key] }.join : number.to_s
  end
end
