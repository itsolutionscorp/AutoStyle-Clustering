class Series
  attr_reader :digits
  def initialize(string)
    @digits = string.split("").map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > digits.length

    (digits.length - size + 1).times.with_object([]) do |num, result|
      result << digits[(num)..(size + num - 1)]
    end
  end
end
