class Hexadecimal
  HEX_MAPPINGS = {a: 10, b: 11, c: 12, d: 13, e: 14, f: 15}

  def initialize(str)
    @hex = sanitize(str)
  end

  def to_decimal
    @hex.chars.to_a.reverse.each_with_index.inject(0) do |sum, (h, i)|
      sum += decimal_value(h)*(16**i)
    end
  end

  def decimal_value(h)
    if h =~ /[a-f]/
      HEX_MAPPINGS[h.to_sym]
    else
      h.to_i
    end
  end

  private

  def sanitize(str)
    str =~ /[^0-9a-f]+/ ? '0' : str
  end
end
