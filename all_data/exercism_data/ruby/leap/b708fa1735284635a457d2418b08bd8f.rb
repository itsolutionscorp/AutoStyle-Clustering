class Year
  def self.leap?(date)
    date % 4 == 0 && (date % 100 != 0 || date % 400 == 0)
  end
end
