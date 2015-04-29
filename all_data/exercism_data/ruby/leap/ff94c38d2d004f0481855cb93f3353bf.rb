class Fixnum
  def multiple_of_4?
    self % 4 == 0
  end
  def multiple_of_100?
    self % 100 == 0
  end
  def multiple_of_400?
    self % 400 == 0
  end
end

class Year
  def self.leap?(year)
    year.multiple_of_4? and !year.multiple_of_100? or year.multiple_of_400?

  end
end
