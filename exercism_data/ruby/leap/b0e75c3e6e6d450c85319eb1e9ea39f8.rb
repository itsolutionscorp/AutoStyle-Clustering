class Year
  def self.leap? year
    is_fourth_century?(year) || (year % 4 == 0 && !is_century?(year))
  end

  def self.is_fourth_century? year
    year % 400  == 0
  end
  def self.is_century? year
    year % 100  == 0
  end

end
