require 'prime'
class Raindrops
  def self.convert(number)
    answer = ''
    primes = Prime.prime_division(number).flatten
    if primes.include? 3 
      answer += 'Pling'
    end
    if primes.include? 5 
      answer += 'Plang'
    end
    if primes.include? 7 
      answer += 'Plong'
    end
    if answer == '' then
      number.to_s
    else
      answer
    end
  end
end
