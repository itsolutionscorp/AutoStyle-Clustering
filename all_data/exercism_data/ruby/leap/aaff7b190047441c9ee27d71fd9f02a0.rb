class Year
  def self.leap? y
    (y % 4 == 0) and (y % 100 != 0) or (y % 400 == 0)
  end
end
