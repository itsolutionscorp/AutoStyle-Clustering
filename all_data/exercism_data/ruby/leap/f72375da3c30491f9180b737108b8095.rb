class Year
  def self.leap?(year)
    fourth_century?(year) || fourth_year?(year) && century?(year)
  end

private

  def self.fourth_year?(year)
    year % 4 == 0
  end

  def self.century?(year)
    year % 100 != 0
  end

  def self.fourth_century?(year)
    year % 400 == 0
  end
end
