require 'prime'

class Raindrops
  def self.convert(number)
    prime_roots = number.prime_division.map do |x,y| x end
    answer = ''
    answer += 'Pling' if prime_roots.include? 3
    answer += 'Plang' if prime_roots.include? 5
    answer += 'Plong' if prime_roots.include? 7
    answer = number.to_s if answer == ''
    return answer
  end
end
