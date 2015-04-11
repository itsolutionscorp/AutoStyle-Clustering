class Raindrops
  DROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  class << self
    def convert(number)
      conversion = DROPS.select do |factor, drop|
        divisible_by?(number, factor)
      end.values.join
      conversion.empty? ? number.to_s : conversion
    end

    def divisible_by?(number, factor)
      number.modulo(factor).zero?
    end

    private :divisible_by?
  end
end
