require 'prime'
module Raindrops
  PRIMES    = [3, 5, 7]
  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    if (raindrops = raindrops_for(number)).any?
      raindrops.join
    else
      number.to_s
    end
  end

  private

  def self.raindrops_for(number)
    raindrops[number] ||= begin
      PRIMES.map { |prime| RAINDROPS[prime] if (number % prime).zero? }.compact
    end
  end

  def self.raindrops
    @raindrops ||= {}
  end
end
