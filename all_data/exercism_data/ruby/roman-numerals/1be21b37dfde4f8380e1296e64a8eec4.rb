class Fixnum
  def to_roman
    FixnumToRomanConverter.new(self).to_roman
  end
end

class FixnumToRomanConverter
  def initialize(fixnum)
    @numbers = fixnum.to_s.chars
  end

  def to_roman
    (0...@numbers.size).to_a.reverse.zip(@numbers).map do |place_value, numeral|
      convert(place_value, numeral)
    end.join
  end

  private

  MAP = [['I'], ['X', 'V'], ['C', 'L'], ['M', 'D']].freeze

  def convert(place, numeral)
    case numeral = numeral.to_i
    when 1,2,3
      MAP[place][0] * numeral
    when 4
      MAP[place][0] + MAP[place+1][1]
    when 5
      MAP[place+1][1]
    when 6,7,8
      MAP[place+1][1] + (MAP[place][0] * (numeral % 5))
    when 9
      MAP[place][0] + MAP[place+1][0]
    end
  end

  def numerals_sequence(n)
    return [] if n < 1
    prime = HASHMAP.keys.sort.reverse.select { |p| n % p == 0 }.first
    return [prime] + numerals_sequence(n - prime)
  end
end
