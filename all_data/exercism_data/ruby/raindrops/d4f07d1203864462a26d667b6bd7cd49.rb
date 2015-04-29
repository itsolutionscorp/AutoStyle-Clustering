########## Raindrops Exercism Problem ##############
class Raindrops
  class << self

    def convert(number)
      add_sounds(number) || number.to_s
    end

    private

    FACTOR_STRING_TABLE = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    def add_sounds(number)
      new_string = ''
      FACTOR_STRING_TABLE.each do |factor, string|
        new_string << string if factor?(number, factor)
      end

      new_string.empty? ? nil : new_string
    end

    def factor?(integer, factor)
      (integer % factor).zero?
    end
  end
end
