class Year
  def self.leap?(year)
    if imposter_leap(year) && exceptional_leap(year)
      true
    elsif imposter_leap(year)
      false
    elsif standard_leap(year)
      true
    else
      false
    end
  end

  private

  def self.standard_leap(year)
    year%4 == 0
  end

  def self.imposter_leap(year)
    year%4 == 0 && year%100 == 0
  end

  def self.exceptional_leap(year)
    year%100 == 0 && year%400 == 0
  end
end
