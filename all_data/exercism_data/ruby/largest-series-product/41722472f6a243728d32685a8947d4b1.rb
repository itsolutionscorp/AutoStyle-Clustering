class Series
  def initialize(string_of_numbers)
    @string_of_numbers = string_of_numbers
  end

  def largest_product(size)
    products = slices(size).collect do |slice|
      slice.reduce(1, :*)
    end
    product = products.sort.reverse.first
    product.nil? ? 1 : product
  end

  def slices(size)
    raise ArgumentError if size > @string_of_numbers.length
    sliced = []
    digits.each_with_index do |digit, index|
      sliced << digits[index..index + size - 1] unless digits[index + size - 1].nil?
    end
    return sliced
  end

  def digits
    @string_of_numbers.scan(/./).map { |x| x.to_i }
  end
end
