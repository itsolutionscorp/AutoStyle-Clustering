class Series

  def initialize(str)
    @digits = str.chars.map { |c| c.to_i }
  end

  def slices(nb)
    raise ArgumentError unless nb <= digits.length
    (digits.length - nb + 1)
      .times
      .map { |i| digits[i,nb] }
  end

  private

  attr_reader :digits

end
