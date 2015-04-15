class Binary

  def initialize(number)
    @number = number
  end

  def to_decimal
    decimal = 0

    @number.each_char.with_index do |char, idx|
      if !char.match(/[0-9]/)
        decimal = 0
        break
      end
      decimal += char.to_i * 2**(@number.to_s.length-1 - idx)
    end

    decimal
  end
end
