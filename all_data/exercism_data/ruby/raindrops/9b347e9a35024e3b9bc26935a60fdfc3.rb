class Raindrops

  # Order is important
  RAINDROP_SPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    string = ''

    RAINDROP_SPEAK.each { |k,v|
      string += v if number.modulo(k).zero?
    }

    string.empty? ? number.to_s : string
  end

end
