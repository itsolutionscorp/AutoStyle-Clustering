class Raindrops
  FACTORS = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert (num)
    str = ''
    FACTORS.each { |key, value|
      if num % key == 0
        str += value
      end
    }
    if str.length == 0
      str = num.to_s
    end
    str
  end
end
