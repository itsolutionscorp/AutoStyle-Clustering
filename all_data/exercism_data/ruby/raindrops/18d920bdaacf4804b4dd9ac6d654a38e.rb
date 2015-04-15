class Raindrops

  FACTOR_TRANSLATIONS = {
    3 => 'Pling',
    5 =>  'Plang',
    7 => 'Plong'
  }

  def self.convert num
    rain_words = ''

    FACTOR_TRANSLATIONS.each do |prime, translation|
        rain_words += translation if num % prime == 0
    end

    rain_words += num.to_s if rain_words.empty?
    rain_words
  end

end
