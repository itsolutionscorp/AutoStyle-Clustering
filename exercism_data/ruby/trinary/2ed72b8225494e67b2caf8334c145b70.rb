class Trinary
  def initialize(trinary_number)
    @trinary_number = trinary_number
  end

  def to_decimal
    return 0 unless valid_trinary_number?(@trinary_number) 

    trinary_number = @trinary_number.reverse
      .split('')
      .map(&:to_i)

    trinary_number.each_with_index.reduce(0) do |sum, (number, index)|
      sum += 3 ** index * number
    end
  end

  private

  def valid_trinary_number?(number)
    number.match(/\A[012]+\z/)
  end
end
