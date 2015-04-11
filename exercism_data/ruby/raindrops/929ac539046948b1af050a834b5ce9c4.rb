require 'prime'

class Raindrops
  def self.convert(number)
    result = ''
    Prime.prime_division(number).flatten.uniq.each do |prime|
      result += 'Pling' if prime.eql? 3
      result += 'Plang' if prime.eql? 5
      result += 'Plong' if prime.eql? 7
    end
    result.empty? ? number.to_s : result
  end
end
