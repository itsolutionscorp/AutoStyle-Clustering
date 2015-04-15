class Binary
  attr_reader :number

  def initialize(num)
    @number = num
  end

  def to_decimal
    return 0 if not_binary?

    res = 0
    number.chars.reverse.each_with_index do |char, i|
      res += char.to_i * (2 ** i)
    end
    res
  end

  private
    def not_binary?
      !!(number.match /[^0-1]/)
    end
end
