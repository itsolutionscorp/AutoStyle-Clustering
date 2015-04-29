module Year
  extend self

  def leap?(year)
    @year = year
    case
      when year_divisible_by(400)  then true
      when year_divisible_by(100)  then false
      when year_divisible_by(4)    then true
      else false
    end
  end

  private

  def year_divisible_by(divisor)
    @year.modulo(divisor).zero?
  end
end
