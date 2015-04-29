class Raindrops

  SOUND = { 3 => 'Pling',
            5 => 'Plang',
            7 => 'Plong'
          }

  def self.convert(num)
    to_return = SOUND.map do |divisor, sound|
      divis(num, divisor, sound)
    end.join

    if to_return == ""
      num.to_s
    else
      to_return
    end
  end

  def self.divis(number, divisor, sound)
    sound if number % divisor == 0
  end

end
