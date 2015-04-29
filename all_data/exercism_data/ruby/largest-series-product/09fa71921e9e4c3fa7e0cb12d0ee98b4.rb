class Series

  attr_reader :digits

  def initialize(string)
    @digits = string.scan(/\d/).map(&:to_i)
  end

  def slices(n)
    digits[0..-n].map.with_index do |d,i|
      (i..i+n-1).map do |digit| 
        digits[digit]
      end
    end
  end

  def largest_product(n)
    raise ArgumentError if n > digits.size
    sum_slices(n)
  end

  private
  def sum_slices(n)
    return 1 if digits.size == 0
    slices(n).map do |array| 
      array.reduce(:*) 
    end.sort.last
  end

end
