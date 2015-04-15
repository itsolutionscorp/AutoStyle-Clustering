require 'prime'
class Raindrops
  def self.convert number
    plengy = {pling: 3, plang: 5, plong: 7}.map {|word, expected| word.capitalize if Prime.prime_division(number).flatten.include? expected}.join
    plengy.empty? ? number.to_s : plengy
  end
end
