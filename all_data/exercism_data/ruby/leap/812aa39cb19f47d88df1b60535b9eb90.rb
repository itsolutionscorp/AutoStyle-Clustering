class Year

  def self.leap?(year)
    divided_evenly?(4,   year) and not
    divided_evenly?(100, year) or
    divided_evenly?(400, year)
  end

  private
    def self.divided_evenly?(divisor, year)
      year % divisor == 0
    end

end
