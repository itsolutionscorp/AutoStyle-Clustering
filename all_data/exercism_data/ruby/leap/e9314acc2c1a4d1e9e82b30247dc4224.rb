class Year

  def self.leap?(year)
    raise ArgumentError, 'Argument is not a year' unless(year.is_a? Integer)

    (year % 4 == 0) && (!(year % 100 == 0) || (year % 400 == 0))
  end

end
