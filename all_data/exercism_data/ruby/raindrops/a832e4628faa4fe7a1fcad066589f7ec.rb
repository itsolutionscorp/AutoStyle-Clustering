class Raindrops

  @@conversions = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  
  def self.convert( num )
    number = Divisible.new num

    factors = @@conversions.select { |divisor, sound| number.divisible_by? divisor }
    factors.empty? ? number.to_s : factors.values.join
  end

  class Divisible

    def initialize(number)
      @number = number
    end

    def divisible_by?( divisor )
      @number % divisor == 0
    end

    def to_s
      @number.to_s
    end
  end

end
