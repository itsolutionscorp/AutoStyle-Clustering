require 'prime'

class Raindrops
  NUMBER_TO_RAINDROP = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
  NUMBER_TO_RAINDROP.default = ''

  def convert(number)
    factors = Prime.prime_division(number).map(&:first)

    result = factors.map { |factor| NUMBER_TO_RAINDROP[factor] }.join
    result = number.to_s if result.empty?
    result
  end
end
