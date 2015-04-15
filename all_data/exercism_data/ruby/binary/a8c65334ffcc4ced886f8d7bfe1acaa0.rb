class Binary
  attr_reader :to_decimal

  def initialize str
    @stream = str.chars
    @to_decimal ||= convert_to_decimal
  end

  private
    def convert_to_decimal
      return 0 if !@stream.all? { |d| d=='1' || d=='0' }
      @stream.each.inject(0) do |decimal,digit|
        decimal*2 + digit.to_i
      end
    end
end
