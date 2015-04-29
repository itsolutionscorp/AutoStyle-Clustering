class Raindrops
  @@raindrops = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    uniq_factors = self.get_uniq_factors(number)
    uniq_factors.any? { |factor| [3,5,7].include? factor } ? uniq_factors.uniq.map { |n| @@raindrops[n] }.join : uniq_factors.reduce(:*).to_s
  end

  private

  def self.get_uniq_factors(number, factors=[])
    return [1] if number == 1

    (2..number).each do |denominator|
      if number % denominator == 0
        factors << denominator
        self.get_uniq_factors(number/denominator, factors)
        break
      end
    end

    factors
  end
end

p Raindrops.convert(28)
p Raindrops.convert(1755)
p Raindrops.convert(34)
p Raindrops.convert(1)
