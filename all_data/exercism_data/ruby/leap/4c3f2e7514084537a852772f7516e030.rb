class Year

  def self.leap?(year)
    if year % 4 == 0
      return true unless non_leap_century?(year)
    end
    false
  end

  private
    def self.non_leap_century?(year)
      year % 100 == 0 && year % 400 != 0
    end

end
