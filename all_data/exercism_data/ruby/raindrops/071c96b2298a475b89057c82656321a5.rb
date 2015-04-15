class Raindrops
  def self.convert number
    answer = ''
    if (number % 3 == 0) then  answer += 'Pling'
    end
    if (number % 5 == 0) then  answer += 'Plang'
    end
    if (number % 7 == 0) then  answer += 'Plong'
    end
    answer.length == 0 ? number.to_s : answer
  end
end
