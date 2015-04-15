class Raindrops
  class << self
    def convert(number)
      raindrops = raindrop_translations.map do |prime, message|
        message if disible_by?(number, prime)
      end
      raindrops.compact!

      raindrops.empty? ? number.to_s : raindrops.join
    end

    private

    def disible_by?(number, modulo)
      (number % modulo).zero?
    end

    def raindrop_translations
      { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    end
  end
end
