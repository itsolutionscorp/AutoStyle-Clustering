# Year class
class Year
  def self.leap?(year)
    if (year % 4 == 0 && year % 100 != 0) ||
      (year % 100 == 0 && year % 400 == 0)
      'OK'
    end
  end
end
