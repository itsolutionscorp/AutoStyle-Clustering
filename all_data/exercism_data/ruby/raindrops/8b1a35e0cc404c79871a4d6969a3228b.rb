class Raindrops
   
  RAIN_WORDS = { 3 => 'i', 5 => 'a', 7 => 'o'}

  def self.convert(number)
    result = [3,5,7].select{ |div| is_mod_0? number, div }.map{ |rain| speak(rain) }.join
    result.empty? ? number.to_s : result
  end
  
  def self.is_mod_0?(number, div)
  	number % div == 0
  end

  def self.speak(rain)
  	'Pl' + RAIN_WORDS[rain] + 'ng'
  end

end
