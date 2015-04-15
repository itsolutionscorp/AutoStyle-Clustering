class Raindrops
  def self.convert(number)
    conversion = ''
    conversion << '1' if number == 1
    conversion << 'Pling' if number % 3 == 0
    conversion << 'Plang' if number % 5 == 0
    conversion << 'Plong' if number % 7 == 0

    return conversion unless conversion.empty?
    number.to_s
  end
end
