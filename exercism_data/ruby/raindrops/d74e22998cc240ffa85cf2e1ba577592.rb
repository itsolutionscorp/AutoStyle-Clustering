class Raindrops

  SOUND = { 3 => 'Pling',
            5 => 'Plang',
            7 => 'Plong'
          }

  def self.convert(number)
    conversion = ''
    SOUND.each { |factor, sound| conversion << sound if number.modulo(factor).zero? }
    conversion << number.to_s if conversion == ''
    conversion
  end
end
