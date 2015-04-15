require 'pry'
class Raindrops

  CONVERSION = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']]

  def self.convert(number)
    return '1' if number == 1

    translation = factors(number).map { |factor| translate(factor) }.uniq.join
    translation.empty? ? number.to_s : translation
  end

  private

  def self.translate(number)
    CONVERSION.select { |code, translation| code == number }.flatten.last
  end

  def self.factors(n)
    return [] if n == 1
    factor = (2..n).detect {|x| n % x == 0}
    [factor] + factors(n / factor)
  end
end
