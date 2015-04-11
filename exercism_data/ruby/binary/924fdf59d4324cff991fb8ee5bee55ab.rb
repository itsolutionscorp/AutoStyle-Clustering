class Binary
  attr_reader :to_decimal

  def initialize str
    @binary = str.match(/[^01]/) ? '0' : str
    @to_decimal ||= convert_to_decimal
  end

  private
    def convert_to_decimal
      @binary.each_char.inject(0) do |decimal,digit|
        decimal*2 + digit.to_i
      end
    end
end
