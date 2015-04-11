class Integer
  def divisible_by? number
    self % number == 0
  end
end

class Raindrops
  def convert(number)
    result = ''
    result += 'Pling' if number.divisible_by? 3
    result += 'Plang' if number.divisible_by? 5
    result += 'Plong' if number.divisible_by? 7
    result = number.to_s if result.empty?
    result
  end
end
