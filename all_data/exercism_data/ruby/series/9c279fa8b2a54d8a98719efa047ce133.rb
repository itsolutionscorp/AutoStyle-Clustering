class Series

  def initialize(str)
    @digits = str.chars.map(&:to_i)
  end

  def slices(nb)
    raise ArgumentError unless nb <= digits.length
    digits.each_cons(nb).to_a
  end

  private

  attr_reader :digits

end
