class Binary
  def initialize(bin_string)
    @bin_string = bin_string
  end
  def to_decimal
    if /^[01]+$/ =~ @bin_string then
      @bin_string.chars.each_with_index.inject(0){ |dec, (c, i)| 
        dec + (c == '1' ? 2**(@bin_string.length - i - 1) :  0)
      }
    else
      0
    end
  end
end
