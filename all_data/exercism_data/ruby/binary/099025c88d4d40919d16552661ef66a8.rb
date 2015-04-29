class Binary
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def self.base
    2
  end

  def to_decimal
    return 0 if string.match /\D/

    sum   = 0
    count = 0

    string.split(//).each do |column|
      power = (base_power - count)

      sum += column.to_i * Binary.base ** power

      count += 1
    end

    sum
  end

  private

  def base_power
    @base_power ||= string.size - 1
  end
end
