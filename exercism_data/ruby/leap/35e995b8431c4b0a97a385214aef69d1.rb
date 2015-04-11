class Year
  def self.leap?(y)
    (y % 4 == 0 && y % 100 != 0) || y % 400 == 0 ? true : false
  end
end
