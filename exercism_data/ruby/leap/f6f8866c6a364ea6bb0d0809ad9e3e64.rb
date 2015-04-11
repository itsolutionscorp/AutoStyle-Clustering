class Year

  def self.leap?(year)
    @year = year
    div_by(4) && (div_by(400) || ! div_by(100))
  end

  private

  def self.div_by(number)
    @year % number == 0 if @year
  end

end
