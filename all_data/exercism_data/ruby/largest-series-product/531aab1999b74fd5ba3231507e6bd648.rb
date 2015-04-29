class Series
  attr_reader :digits

  def initialize str
    @digits = str.chars.select { |c| c.match /[0-9]/ }.map(&:to_i)
  end

  def slices n
    @digits.each_cons(n).to_a
  end

  def largest_product n
    return 1 if n == 0
    fail ArgumentError if n > digits.size
    slices(n).map { |s| s.reduce(1) { |e, a| a * e } }.sort.last
  end
end
