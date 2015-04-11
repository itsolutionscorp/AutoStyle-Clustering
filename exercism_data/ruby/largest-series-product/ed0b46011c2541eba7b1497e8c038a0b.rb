class Series
  def initialize(input_string)
    @input_string = input_string
  end

  def digits
    @digits ||= @input_string.scan(/\d/).map { |i| i.to_i }
  end

  def slices(n)
    raise ArgumentError, "slices exceed number of digits!" if n > digits.length

    groups = digits.length - n + 1
    groups.times.map { |i| digits.slice(i, n) }
  end

  def largest_product(n)
    slices(n).reduce(1) do |largest, group|
      product = group.reduce(&:*) || 0
      largest > product ? largest : product
    end
  end

end
