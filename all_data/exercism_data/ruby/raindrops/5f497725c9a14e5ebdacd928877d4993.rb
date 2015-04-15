class Raindrops
  def self.convert(rain)
    soundvalues = [[3,'Pling'],[5,'Plang'],[7,'Plong']]

    phrase = soundvalues.map {
      |divisor, sound| sound if rain % divisor == 0
    }.join

    phrase.length == 0 ? rain.to_s : phrase
  end
end
