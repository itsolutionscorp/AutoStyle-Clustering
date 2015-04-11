class Raindrops
  def self.convert(number)
    raindrops = raindrop_translations.map do |prime, message|
      message if disible_by?(number, prime)
    end
    raindrops.compact!

    raindrops.empty? ? number.to_s : raindrops.join
  end

  private

  def self.disible_by?(number, modulo)
    (number % modulo).zero?
  end

  def self.raindrop_translations
    { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  end
end
