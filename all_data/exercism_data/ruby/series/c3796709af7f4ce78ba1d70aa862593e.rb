class Series
  def initialize(s)
    @length = s.length
    @s = s.chars.map(&:to_i)
  end

  def slices(digit)
    result = []
    number_of_slices = @length - digit + 1

    if digit > @length
      raise ArgumentError
    else
      number_of_slices.times do |idx|
        result << @s[idx, digit]
      end
      result
    end
  end
end
