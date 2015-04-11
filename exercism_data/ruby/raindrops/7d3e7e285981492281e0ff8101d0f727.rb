class Raindrops
  def self.convert(number)
    conversion = ''
    conversion << 'Pling' if number.modulo(3).zero?
    conversion << 'Plang' if number.modulo(5).zero?
    conversion << 'Plong' if number.modulo(7).zero?
    conversion << number.to_s if conversion == ''
    conversion
  end
end
