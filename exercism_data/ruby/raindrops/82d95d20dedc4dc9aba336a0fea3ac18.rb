class Raindrops
  def self.convert(number)
    value = ''
    value += 'Pling' if (number % 3).zero?
    value += 'Plang' if (number % 5).zero?
    value += 'Plong' if (number % 7).zero?
    value = number.to_s if value == ''
    return value
  end
end
