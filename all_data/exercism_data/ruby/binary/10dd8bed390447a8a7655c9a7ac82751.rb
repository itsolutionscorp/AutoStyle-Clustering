class Binary
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    # Invalid strings return 0
    return 0 if @binary_string !~ /\A[01]+\z/
    # Sort from least to most significant digits and add index
    indexed_digits = @binary_string.reverse.split("").each_with_index.to_a
    # Convert ones to 2^index, and sum.
    indexed_digits.map{ |tuple| convert_indexed_digit(tuple)}.reduce(0, :+)
  end

  private

  def convert_indexed_digit(tuple)
    (tuple[0]=="1") ? (2 ** tuple[1]) : 0
  end
end
