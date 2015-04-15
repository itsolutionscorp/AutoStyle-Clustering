require 'prime'

class Raindrops < Prime
  
  def self.convert(number)
    answer = ""
    answers = {3 => 'Pling',5 => 'Plang',7 => 'Plong'}
  
    answers.each do |key, value|
      if number % key == 0
        answer << answers[key]
      end
    end
      
    if answer == ""      
      answer = number.to_s
    end

    answer

  end
end
