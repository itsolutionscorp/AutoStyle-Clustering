class Raindrops
  def self.convert(number)
    result = ''
    if number % 3 == 0 then result += 'Pling' end
    if number % 5 == 0 then result += 'Plang' end
    if number % 7 == 0 then result += 'Plong' end

    if result == ''
      return number.to_s
    else
      return result
    end
  end
end
