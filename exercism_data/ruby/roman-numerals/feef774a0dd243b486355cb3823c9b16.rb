require 'pry'
class Fixnum

  def to_roman
    number = self
    answer = ''
    numerals = [['I', 'V'], ['X', 'L'], ['C', 'D'], ['M']]
    i = 0
    while number > 0
      working_number = number % 10
      temp = ''
      if working_number == 4
        temp = numerals[i][0] + numerals[i][1]
      elsif working_number == 9
        temp = numerals[i][0] + numerals[i+1][0]
      else
        if working_number >= 5
          temp += numerals[i][1]
          working_number -= 5
        end
        working_number.times { temp += numerals[i][0] }
      end
      answer = temp + answer
      number /= 10
      i += 1
    end
    return answer
  end

end
