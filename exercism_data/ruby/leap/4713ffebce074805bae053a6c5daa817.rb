module Year
  extend self

  def leap?(year)
    case (y = Integer(year))
    when divisible_by?(400) then true
    when divisible_by?(100) then false
    else divisible_by?(4).(y)
    end
  end

  def divisible_by?(divisor)
    lambda { |dividend| (dividend % divisor).zero? }
  end
end
