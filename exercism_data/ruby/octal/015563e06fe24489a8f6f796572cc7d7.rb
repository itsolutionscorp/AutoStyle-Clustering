class Octal
  attr_reader :strchars, :base
  def initialize(str)
    @strchars = str.chars
    @base = 8
  end

  def to_decimal
    (0..strchars.size-1).inject(0) do |decimal, ndx|
      char = strchars.pop
      return 0 unless valid? char
      decimal += char.to_i*(base**ndx)
    end
  end

  def valid?(char)
    valid_chars.include? char
  end

  def valid_chars
    [*('0'..(base-1).to_s)]
  end
end
