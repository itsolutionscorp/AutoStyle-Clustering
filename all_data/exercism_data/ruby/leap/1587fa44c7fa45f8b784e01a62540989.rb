class Year
  class << self
    def leap?(year)
      @year = year
      fourth? && ( not_century? || fourth_hundred? )
    end

    def fourth?
      nth?(4)
    end

    def not_century?
      !century?
    end

    def century?
      nth?(100)
    end

    def fourth_hundred?
      nth?(400)
    end

    def nth?(n)
      @year % n == 0 
    end
  end
end
