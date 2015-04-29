class Binary
  def initialize(binary)
    @bin = binary.reverse
  end
  def to_decimal
    if @bin =~ /[^10]/
      0
    else
      dec = 0
      @bin.each_char.with_index do |character, index|
        num = (character.to_i)*(2**index)
        dec += num
      end
      dec
    end
  end
end
