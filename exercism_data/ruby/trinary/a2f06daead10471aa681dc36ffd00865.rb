class Trinary
  attr_reader :to_decimal
  def initialize (trinary)
    @to_decimal = []

    trinary =~ /[^012]/ ?  @to_decimal = [0] : trinary = trinary.reverse!.split("")

    trinary.each_with_index { |digit,i| @to_decimal << digit.to_i*(3**i)} unless @to_decimal == [0]
    @to_decimal = @to_decimal.inject( :+)
  end
end
