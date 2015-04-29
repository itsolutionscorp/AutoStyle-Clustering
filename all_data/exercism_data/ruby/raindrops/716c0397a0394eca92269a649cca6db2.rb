require 'prime'

module Raindrops
  def self.convert(number)
    fail ArgumentError unless number.is_a?(Integer) 
    fail RangError if number < 1

    return '1' if 1 == number

    factors = Prime.prime_division(number).map { |prime, _| prime }
    p357 = factors.select { |p| [3, 5, 7].include? p }

    if p357.empty?
      number.to_s
    else
      p357.map do |i|
        case i
        when 3 then 'Pling'
        when 5 then 'Plang'
        when 7 then 'Plong'
        end
      end.join
    end
  end
end
