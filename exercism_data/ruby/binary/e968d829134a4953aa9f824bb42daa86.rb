class Binary
  def initialize(string)
    @decimal = 0
    # silently ignore invalid strings while leaving @decimal as 0
    if string !~ /[^01]/
      string.each_char do |bit|
        @decimal *= 2
        @decimal += 1 if bit == "1"
      end
    end
  end

  def to_decimal
    @decimal
  end
end
