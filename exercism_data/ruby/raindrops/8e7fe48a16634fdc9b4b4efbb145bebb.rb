class Raindrops

  def self.convert(number)
    num_save = []
    num_save.push(number)
    answer = ''

    if number % 3 == 0
      answer += "Pling"
      number = number / 3
    end

    if number % 5 == 0
      answer += "Plang"
      number = number / 5
    end

    if number % 7 == 0
      answer += "Plong"
    end


    if answer.empty?
      answer = num_save.first.to_s
    end

    return answer
  end

end
