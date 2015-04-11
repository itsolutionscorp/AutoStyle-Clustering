module Raindrops

  module ClassMethods

    RAINDROP_SPEAK = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    def convert(number)
      know_what_to_make_of_it?(number) ? translate(number) : number.to_s
    end

    def factor?(number, factor)
      number % factor == 0
    end

    def know_what_to_make_of_it?(number)
      RAINDROP_SPEAK.keys.any? do |prime|
        factor?(number, prime)
      end
    end

    def translate(number)
      "".tap do |message|
        RAINDROP_SPEAK.each do |prime, translation|
          message << translation if factor?(number, prime)
        end
      end
    end

  end

  extend ClassMethods

end
