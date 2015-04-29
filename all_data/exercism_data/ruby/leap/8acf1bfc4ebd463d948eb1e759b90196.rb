class Year
  def self.leap?(year)
    @year = year

    new_century? ? leap_century? : leap_year?
  end

private
  def self.new_century?
    @year % 100 == 0
  end

  def self.leap_century?
    @year % 400 == 0
  end

  def self.leap_year?
    @year % 4 == 0
  end
end
