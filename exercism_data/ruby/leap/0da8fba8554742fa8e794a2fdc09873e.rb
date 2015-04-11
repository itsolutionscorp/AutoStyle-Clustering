class Year
  def initialize year
    @year = year
  end

  def leap?
    (quadrennial? && !centennial?) || quadricentennial?
  end

  private

  def quadrennial?
    divisible_by? 4
  end

  def centennial?
    divisible_by? 100
  end

  def quadricentennial?
    divisible_by? 400
  end

  def divisible_by? years
    (@year % years).zero?
  end
end
