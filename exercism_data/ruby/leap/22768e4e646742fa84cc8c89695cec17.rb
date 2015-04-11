module Year
  # on every year that is evenly divisible by 4
  #   except every year that is evenly divisible by 100
  #     unless the year is also evenly divisible by 400
  def self.leap? year
    return true if fourth_century?(year)
    return true if fourth_year?(year) && !century?(year)
    return false
  end

  private

  def self.century? year
    year % 100 == 0
  end

  def self.fourth_century? year
    year % 400 == 0
  end

  def self.fourth_year? year
    year % 4 == 0
  end

end
