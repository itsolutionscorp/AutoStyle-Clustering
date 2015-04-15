class Raindrops

  FACTOR_TRANSLATIONS = {
    3 => 'Pling',
    5 =>  'Plang',
    7 => 'Plong'
  }

  def self.convert num
    
    rain_words = FACTOR_TRANSLATIONS.map do |prime_num, translation|
        translation if num % prime_num == 0
    end.compact.join

    rain_words.empty? ? rain_words << num.to_s : rain_words

  end

end
