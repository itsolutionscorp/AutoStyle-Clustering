class Raindrops
  class << self
    def convert(number)
      conversion = ''
      if divisible_by?(number, 3)
        conversion << 'Pling'
      end
      if divisible_by?(number, 5)
        conversion << 'Plang'
      end
      if divisible_by?(number, 7)
        conversion << 'Plong'
      end
      if conversion.empty?
        conversion << number.to_s
      end
      conversion
    end

    def divisible_by?(number, factor)
      number.modulo(factor).zero?
    end
    private :divisible_by?
  end
end
