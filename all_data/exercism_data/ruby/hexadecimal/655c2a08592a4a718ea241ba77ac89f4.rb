class Hexadecimal
  class Character
    attr_reader :string
    def initialize(string)
      @string = string
    end

    def to_i
      character_to_values[string.upcase]
    end

    private

    def character_to_values
      {
        '0' => 0,
        '1' => 1,
        '2' => 2,
        '3' => 3,
        '4' => 4,
        '5' => 5,
        '6' => 6,
        '7' => 7,
        '8' => 8,
        '9' => 9,
        'A' => 10,
        'B' => 11,
        'C' => 12,
        'D' => 13,
        'E' => 14,
        'F' => 15
      }
    end
  end

  attr_reader :as_string
  def initialize(asString)
    @as_string = asString
  end

  def to_decimal
    return 0 if as_string.match(/[^0-9abcdef]/)
    components.reverse.each_with_index.reduce(0) do |total, (current, index)|
      total + Character.new(current).to_i * (16**index)
    end
  end

  private

  def components
    as_string.split('').to_a
  end
end
