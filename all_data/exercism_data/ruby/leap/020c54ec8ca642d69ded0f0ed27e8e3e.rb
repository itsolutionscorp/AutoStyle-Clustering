class Year
  def self.leap?(year)
    non_century_leap_year?(year) || fourth_century_leap_year?(year)
  end

  private

    def self.non_century_leap_year?(year)
      year % 4 == 0 && year % 100 != 0 && year % 400 != 0
    end

    def self.fourth_century_leap_year?(year)
      year % 4 == 0 && year % 100 == 0 && year % 400 == 0
    end
end
