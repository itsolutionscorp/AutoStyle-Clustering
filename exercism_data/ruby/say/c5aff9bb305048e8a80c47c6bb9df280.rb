class Say
  attr_reader :number, :digits

  def initialize(number)
    @number = number
    @digits = @number.to_s.chars
  end

  def in_english
    raise ArgumentError unless valid?
    return 'zero' if number == 0
    words = UNITS.keys.map do |unit|
      say_unit(digits.pop(3), unit) unless digits.empty?
    end
    words.reverse.join(' ').strip.gsub("  ", " ")
  end

  def valid?
    number >= 0 && number < 1_000_000_000_000
  end

  def say_unit(x, unit)
    x = x.join.to_i
    x == 0 ? '' : say_subunit(x) + ' ' + UNITS[unit]
  end

  def say_subunit(x)
    if x <= 20
      NUMBERS[x]
    else
      hundreds, tens, ones = x/100.floor, x%100/10.floor, x%100%10.floor
      result = []
      result << NUMBERS[hundreds] + ' ' + NUMBERS[100] if hundreds > 0
      result << ' ' + NUMBERS[tens*10] if tens > 0
      result << (tens > 0 ? '-' : ' ') + NUMBERS[ones] if ones > 0
      result.join
    end
  end

  UNITS = { 0 => '', 1_000 => 'thousand', 1_000_000 => 'million', 1_000_000_000 => 'billion' }

  NUMBERS = { 1 => 'one', 2 => 'two', 3 => 'three', 4 => 'four', 5 => 'five', 6 => 'six', 7 => 'seven', 8 => 'eight', 9 => 'nine', 10 => 'ten', 11 => 'eleven', 12 => 'twelve', 13 => 'thirteen', 14 => 'fourteen', 15 => 'fifteen', 16 => 'sixteen', 17 => 'seventeen', 18 => 'eighteen', 19 => 'nineteen', 20 => 'twenty', 30 => 'thirty', 40 => 'forty', 50 => 'fifty', 60 => 'sixty', 70 => 'seventy', 80 => 'eighty', 90 => 'ninety', 100 => 'hundred' }

end
