require 'pry'
class Raindrops

  def self.convert(number)
    answer = ''
    answer += 'Pling' if number % 3 == 0
    answer += 'Plang' if number % 5 == 0
    answer += 'Plong' if number % 7 == 0

    answer = number.to_s if answer == ''
    return answer
  end


end
