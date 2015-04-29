class Year
  def self.leap?(y)
    if y % 400 == 0
      return true
    end
    if y % 100 == 0
      return false
    end
    return y % 4 == 0
  end
end
