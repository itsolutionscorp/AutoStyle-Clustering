class Raindrops
  def self.convert number
    divisible_by_3 = number % 3 == 0
    divisible_by_5 = number % 5 == 0
    divisible_by_7 = number % 7 == 0
    return number.to_s if !divisible_by_3 && !divisible_by_5 && !divisible_by_7
    raindrops = ''
    raindrops += 'Pling' if divisible_by_3
    raindrops += 'Plang' if divisible_by_5
    raindrops += 'Plong' if divisible_by_7
    return raindrops
  end
end
