class Year
  def self.leap? year
    return false if is_odd_year?(year) || is_normal_century?(year)
    true
  end

  def self.is_odd_year? year
    year % 2 != 0
  end

  def self.is_normal_century? year
    year % 100 == 0 && year % 400 != 0
  end
end
