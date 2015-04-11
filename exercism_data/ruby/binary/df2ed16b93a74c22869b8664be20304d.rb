class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    # when binary contains anything but 1 or 0, return 0
    return 0 if /[^10]/.match(@binary)

    binary = @binary.reverse()
      .split('')
      .map(&:to_i)

    binary.each_with_index().reduce(0) do |sum, (number, index)|
      sum += 2 ** index * number
    end
  end
end
