class Hexadecimal

  def initialize(str)
    @str = str.upcase
  end

  def key
    {
      "0" => 0,
      "1" => 1,
      "2" => 2,
      "3" => 3,
      "4" => 4,
      "5" => 5,
      "6" => 6,
      "7" => 7,
      "8" => 8,
      "9" => 9,
      "A" => 10,
      "B" => 11,
      "C" => 12,
      "D" => 13,
      "E" => 14,
      "F" => 15
    }
  end

  def to_decimal
    sum = 0
    keys = key
    if valid?
      @str.reverse.each_char.with_index do |letter, i|
        sum += keys[letter] * 16**i if key[letter]
      end
    end
    sum
  end

  def valid?
    keys = key
    @str.each_char { |letter| return false unless keys.has_key?(letter) }
    true
  end

end
