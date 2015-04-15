class Hexadecimal
  def initialize hex
    @hex = hex
  end

  def to_decimal
    if @hex[/\H/]
      return 0
    end

    @hex.reverse.split("").each_with_index.inject(0) do |sum, (e, i)|
      sum + (to_dec e) * (16**i)
    end
  end

  private
  def to_dec n
    ({ 'a' => '10',
       'b' => '11',
       'c' => '12',
       'd' => '13',
       'e' => '14',
       'f' => '15'}[n] || n).to_i
  end
end
