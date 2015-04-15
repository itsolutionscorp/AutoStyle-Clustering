class Series
  attr_reader :digits

  def initialize(string)
    @digits = if string.match(/^\d+$/)
                string.chars.map(&:to_i)
              else
                []
              end
  end

  def slices(length)
    return [] if length <= 0

    digits.each_cons(length).to_a
  end

  def largest_product(length)
    return 1 if length <= 0
    raise ArgumentError if length > digits.length

    slices(length).map { |args| args.inject(:*) }.max
  end
end
