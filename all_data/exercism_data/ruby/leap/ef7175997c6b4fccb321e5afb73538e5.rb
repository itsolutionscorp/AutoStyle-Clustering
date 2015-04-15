class Year

  class << self
    def leap?(year)
      fourth?(year)  && (!century?(year) || fourth_century?(year))
    end

  protected

    def fourth?(year)
      year % 4 == 0
    end

    def century?(year)
      year % 100 == 0
    end

    def fourth_century?(year)
      year % 400 == 0
    end
  end

end
