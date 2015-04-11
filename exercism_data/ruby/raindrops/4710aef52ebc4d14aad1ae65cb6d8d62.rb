require 'prime'

class Raindrops
  def self.convert(number)
    rain_talk = ""
    prime_factors = number.prime_division.collect(&:first)
    rain_talk += 'Pling' if prime_factors.include? 3
    rain_talk += 'Plang' if prime_factors.include? 5
    rain_talk += 'Plong' if prime_factors.include? 7
    rain_talk += number.to_s if rain_talk.empty?
    rain_talk
  end
end
