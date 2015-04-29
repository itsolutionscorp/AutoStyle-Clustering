class Raindrops
  def self.convert(number)
    converter.(number)
  end

  def self.converter
    RaindropsConverter.new
  end
end

class RaindropsConverter
  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def call(number)
    result = convertNumberToSounds(number)
    format_result(result, number)
  end

  private
  def convertNumberToSounds(number) 
    result = ""
    SOUNDS.each { |prime, sound| result += sound if number % prime == 0 }
    result
  end

  def format_result(result, number)
    result == "" ? number.to_s : result
  end
end
