class Year
  def initialize year
    @year = year
  end

  def leap?
    special_divisor = {400=> true, 100 => false, 4 => true}
    special_divisor.each { |divisor,bool| return bool if divisible_by divisor }
    false
  end

  def divisible_by amount
    @year.modulo(amount).zero?
  end
end
