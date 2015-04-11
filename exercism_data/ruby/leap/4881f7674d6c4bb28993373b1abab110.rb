module Year
  module_function
  def leap?(year)
    raise TypeError if year.class != Fixnum || year < 0
    if year % 4 == 0
      return true if year % 400 == 0
      return false if year % 100 == 0
      return true
    end
    return false
  end
end
