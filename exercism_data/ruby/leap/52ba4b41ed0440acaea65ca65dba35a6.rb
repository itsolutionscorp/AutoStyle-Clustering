Year = Struct.new(:year) do
  def leap?
    leap_candidate? and (leap_century? or not century?)
  end

  private

  def leap_candidate?
    divisible? 4
  end

  def century?
    divisible? 100
  end

  def leap_century?
    divisible? 400
  end

  def divisible?(number)
    year % number == 0
  end
end
