class Trinary
  def initialize(string)
    @decimal = 0
    # silently ignore invalid strings while leaving @decimal as 0
    if string !~ /[^012]/
      string.each_char do |bit|
        @decimal *= 3
        @decimal += bit.to_i
      end
    end
  end

  def to_decimal
    @decimal
  end
end
