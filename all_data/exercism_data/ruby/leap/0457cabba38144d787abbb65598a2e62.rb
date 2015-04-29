# encoding: UTF-8

module Year
  def leap?(year)
    if year % 4 == 0 # on every 4th year
      if year % 100 == 0 # unless it's divisible by 100
        (year % 400 == 0) # except when it's divisible by 400
      else
        true
      end
    else
      false
    end
  end

  module_function :leap?
end
