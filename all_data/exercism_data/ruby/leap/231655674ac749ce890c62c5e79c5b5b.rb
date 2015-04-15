# encoding: UTF-8

module Year
  module_function

  def leap?(year)
    (fourth?(year) && (not_a_century?(year) || fourth_centry?(year)))
  end

  def fourth?(year)
    (year % 4 == 0)
  end

  def not_a_century?(year)
    (year % 100 != 0)
  end

  def fourth_centry?(year)
    (year % 400 == 0)
  end
end
