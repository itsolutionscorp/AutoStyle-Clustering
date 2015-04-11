class Raindrops

  def self.convert(number)
    return number.to_s if number == 0
    output = ''
    output += 'Pling' if number % 3 == 0
    output += 'Plang' if number % 5 == 0
    output += 'Plong' if number % 7 == 0
    output == '' ? number.to_s : output
  end

end
