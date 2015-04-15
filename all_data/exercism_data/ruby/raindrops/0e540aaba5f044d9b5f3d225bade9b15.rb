class Raindrops
   
  RAIN_WORDS = { 3 => 'i', 5 => 'a', 7 => 'o'}

  def self.convert(number)
    (sounds = [3,5,7].map{ |rain| sound_of(rain, number) }.join).empty? ? number.to_s : sounds
  end
  
  def self.sound_of(rain, number)
  	'Pl' + RAIN_WORDS[rain] + 'ng' if number % rain == 0
  end

end
