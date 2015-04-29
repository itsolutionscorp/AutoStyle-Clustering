class Fixnum
  def multiple_of?(mult)
    self % mult == 0
  end
end

class Year
  def self.leap?(year)
    year.multiple_of?(4) and !year.multiple_of?(100) or year.multiple_of?(400)
  end
end
