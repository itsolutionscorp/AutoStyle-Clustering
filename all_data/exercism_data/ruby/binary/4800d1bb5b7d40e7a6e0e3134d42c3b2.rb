class Binary
  def initialize(str)
    @str = str
  end

  def to_decimal
    @str.chars.reduce(0) do | acc, x |
      case x
      when '0'
        acc << 1
      when '1'
        (acc << 1) + 1
      else
        return 0
      end
    end
  end
end
