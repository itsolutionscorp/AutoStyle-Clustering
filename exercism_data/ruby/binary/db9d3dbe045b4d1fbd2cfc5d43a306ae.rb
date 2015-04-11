class Binary
  def initialize binary
    @binary = binary
  end

  def to_decimal
    return 0 if @binary =~ /[^10]/
    decimal = 0
    list = @binary.reverse.chars
    list.each_with_index do |n, i|
      decimal += n.to_i * (2 ** i)
    end
    decimal
  end
end
