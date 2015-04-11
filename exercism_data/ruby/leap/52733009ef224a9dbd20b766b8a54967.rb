class Year
  def self.leap?(y)
    return true if y % 400 == 0
    return false if y % 100 == 0
    return y % 4 == 0
  end
end
