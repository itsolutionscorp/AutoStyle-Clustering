# Convert normal numbers to Roman Numerals
class Roman
  @translations =
  {
    1000 => 'M',
    500  => 'D',
    100  => 'C',
    50   => 'L',
    10   => 'X',
    5    => 'V',
    1    => 'I'
  }

  def self.to_roman(number)
    roman_string = []

    nums = number.to_s.split("")
    count = nums.count

    nums.each do |num|
      num = num.ljust(count, "0").to_i

      while(num > 0) do
        (roman, num) = closest_match(num)
        roman_string << roman
      end

      count -= 1;
    end

    roman_string.join("")
  end

  def self.closest_match(number)
    return special_match(number) if ["4", "9"].include?(number.to_s.split("").first)

    @translations.each do |trans_num, trans_rom|
      return [trans_rom, number - trans_num] if number >= trans_num
    end
  end

  def self.special_match(number)
    Hash[@translations.to_a.reverse].each do |trans_num, trans_rom|
      return ["#{@translations[trans_num - number]}#{trans_rom}", 0] if number < trans_num
    end
  end
end

# Monkey patch Fixnum to route missing method calls to Roman class
class Fixnum
  def method_missing(name, &_block)
    Roman.send(name, self)
  end
end
