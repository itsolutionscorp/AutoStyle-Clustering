# Convert multiples into strings
class Raindrops

  # Convert numbers into specific strings if multiples
  def self.convert(number)
    result = ''
    result << 'Pling' if number % 3 == 0
    result << 'Plang' if number % 5 == 0
    result << 'Plong' if number % 7 == 0
    result << number.to_s if result == ''
    result
  end

end
