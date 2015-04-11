# Raindrops exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

class Raindrops
  def self.convert(number)
    result = ''
    result += 'Pling' if number%3==0
    result += 'Plang'if number%5==0
    result += 'Plong'if number%7==0
    return number.to_s unless !result.empty?
    result
  end
end
