class Year
  def self.leap?(y)
  	(y % 400 == 0) || ((y % 100 != 0) && (y % 4 == 0))
  end
end
