class Year < Struct.new(:year)
  def leap?
    (divisible_by?(400) || (divisible_by?(4) && !divisible_by?(100))) ? true : false
  end

private
  def divisible_by?(num)
    year % num == 0
  end
end