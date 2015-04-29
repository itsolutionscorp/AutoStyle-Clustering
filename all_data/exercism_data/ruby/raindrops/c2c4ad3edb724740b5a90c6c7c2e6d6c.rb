########## Raindrops Exercism Problem ##############
class Raindrops
  FACTOR_STRING_TABLE = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    new_string = ''
    FACTOR_STRING_TABLE.each do |factor, string|
      new_string << string if factor?(number, factor)
    end

    new_string.empty? ? number.to_s : new_string
  end

  def self.factor?(integer, factor)
    integer % factor == 0
  end
end
