class Raindrops
  def self.convert(number)
    result = ''

    raindrop_speak_translations.each do |prime, output|
      result << output if disible_by?(number, prime)
    end

    result.empty? ? number.to_s : result
  end

  private

  def self.disible_by?(number, modulo)
    (number % modulo).zero?
  end

  def self.raindrop_speak_translations
    { 3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong' }
  end
end
