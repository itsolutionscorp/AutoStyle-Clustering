class Year
  class << self

    # Is it a leap year?
    #   on every year that is evenly divisible by 4
    #   except every year that is evenly divisible by 100
    #   unless the year is also evenly divisible by 400
    def leap?(year)
      # TODO: argument validation, year only 
      year.modulo(4).zero? && (!year.modulo(100).zero? || year.modulo(400).zero?)
    end

  end
end
