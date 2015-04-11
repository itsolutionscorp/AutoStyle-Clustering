Year = Struct.new(:year) do
  def leap?
    divisible_by?(4) && !divisible_by?(100) or divisible_by?(400)
  end

  def divisible_by?(n)
    year % n == 0
  end
end
