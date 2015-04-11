# Converts number into Raindrops lingo
class Raindrops
  CONVERT_MAP = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    raindrops = ''
    CONVERT_MAP.each { |key, value| raindrops += value if (number % key) == 0 }
    raindrops.length > 0 ? raindrops : number.to_s
  end
end
