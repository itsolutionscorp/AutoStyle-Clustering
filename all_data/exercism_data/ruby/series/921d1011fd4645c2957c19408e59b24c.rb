class Series
  def initialize(string)
    @digits = string.split('').map(&:to_i)
  end

  attr_reader :digits

  def slices(num)
    if num > @digits.length
      raise ArgumentError
    else
      digits.each_cons(num).to_a
    end
  end
end
