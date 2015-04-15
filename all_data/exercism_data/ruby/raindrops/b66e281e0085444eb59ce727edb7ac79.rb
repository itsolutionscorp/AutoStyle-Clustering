require 'prime'

class Raindrops
  def initialize
    @prime_factors = []
  end

  def convert( number )
    get_prime_factors( number )

    rain_sounds = ""

    @prime_factors.each do | drop |
      case
    when drop == 3
      rain_sounds += "Pling"
    when drop == 5
      rain_sounds += "Plang"
    when drop == 7
      rain_sounds += "Plong"
    end
    end

    rain_sounds += "#{ number }" if rain_sounds.empty?

    rain_sounds
  end

  private
  def get_prime_factors( number )
    if number.prime?
      add_prime_number( number )
    else
      (2..number).each do |divide_me|
        if number % divide_me == 0
          add_prime_number( divide_me ) if divide_me.prime?

          get_prime_factors( number / divide_me)
          break
        end
      end
    end
  end

  def add_prime_number( number )
    @prime_factors << number unless @prime_factors.include?( number )
  end
end
