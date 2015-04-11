class Raindrops
  RAINDROP_WORDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  class << self
    def convert(number)
      in_raindrop = translate_from_dictionary(number)
      in_raindrop.empty? ? number.to_s : in_raindrop
    end

    private

    def translate_from_dictionary(number)
      RAINDROP_WORDS.collect do |factor, translation|
        translation if number % factor == 0
      end.join
    end
  end
end
