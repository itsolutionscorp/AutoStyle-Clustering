class Series
  def initialize(numbers)
    @numbers = numbers.split('')
  end

  def slices(count)
    raise ArgumentError if count > @numbers.length
    result = []
    @numbers.each_with_index do |num, index|
      result << @numbers[index, count].map { |str| str.to_i } if @numbers[index + count - 1]
    end
    result
  end
end
