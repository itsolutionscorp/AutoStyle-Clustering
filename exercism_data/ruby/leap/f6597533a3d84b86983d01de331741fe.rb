class Year

  def self.leap? year
    return true if fourth_century? year
    return false if century? year

    year % 4 == 0
  end

  def self.fourth_century? year
    year % 400 == 0
  end

  def self.century? year
    year % 100 == 0
  end
end
