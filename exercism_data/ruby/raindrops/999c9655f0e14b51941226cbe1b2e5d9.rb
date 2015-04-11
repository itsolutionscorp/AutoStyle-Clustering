class Raindrops
  
  def self.convert( num )
    number = Divisible.new num

    conversion = ''
    if [3, 5, 7].none? { |divisor| number.divisible_by? divisor }
      conversion += number.to_s
    end
    if number.divisible_by? 3
      conversion += 'Pling'
    end
    if number.divisible_by? 5
      conversion += 'Plang'
    end
    if number.divisible_by? 7
      conversion += 'Plong'
    end
    conversion
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
