class Series
  def initialize(serie)
    @serie = serie
  end

  def digits
    @serie.chars.map {|c| c.to_i}
  end

  def slices(num)
    digits.each_cons(num).inject([]){|sum, slice| sum << slice}
  end

  def largest_product(num)
    raise ArgumentError if num > digits.size
    return 1 if num.zero?

    slices(num).map{|slice| slice.inject(&:*)}.max
  end
end
