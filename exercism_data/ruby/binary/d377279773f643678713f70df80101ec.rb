class Binary
  def initialize(binary_string)
    @binary_string = binary_string
  end
  
  def to_decimal
    return 0 unless (@binary_string =~ /\D/).nil?
    binary = @binary_string.chars.map(&:to_i)
    binary.reverse.each.with_index.inject(0) {|sum, (x, i)| sum + x * 2 ** i} 
  end
end
