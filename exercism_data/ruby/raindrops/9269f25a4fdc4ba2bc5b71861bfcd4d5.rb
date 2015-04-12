# Converts number into Raindrops lingo
class Raindrops
  CONVERT_MAP = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    raindrops = ''
    CONVERT_MAP.each { |prime, raindrop| raindrops += raindrop if (number % prime) == 0 }
    raindrops.empty? ? raindrops : number.to_s
  end
end