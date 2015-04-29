class Year
  class << self
    def leap?(year)
      @year = year
      fourth? && ( not_century? || four_hundredth? )
    end

    def fourth?
      nth?(4)
    end

    def not_century?
      !nth?(100)
    end

    def four_hundredth?
      nth?(400)
    end

    def nth?(n)
      @year % n == 0 
    end
  end
end
