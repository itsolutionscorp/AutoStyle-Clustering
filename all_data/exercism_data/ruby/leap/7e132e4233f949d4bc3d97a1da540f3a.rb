class Year
  def self.leap?(date)
    # date is divisible by 4 while not being divisble by 100 unless it is divisble by 400
    date % 400 == 0 || (date % 100 != 0 && date % 4 == 0) ? true : false
  end
end
