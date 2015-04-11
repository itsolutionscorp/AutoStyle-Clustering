class Year
  def self.leap?(year)
    div_4 = year%4 == 0
    div_100 = year%100 == 0
    div_400 = year%400 == 0

    div_400 || div_4 && !div_100
  end
end
