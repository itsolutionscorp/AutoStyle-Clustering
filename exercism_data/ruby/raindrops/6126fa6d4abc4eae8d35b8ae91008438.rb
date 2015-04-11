class Raindrops

  def self.convert(num)
    result = ''

    if num % 3 == 0 then result += 'Pling' end
    if num % 5 == 0 then result += 'Plang' end
    if num % 7 == 0 then result += 'Plong' end

    result == '' ? num.to_s : result
  end

end
