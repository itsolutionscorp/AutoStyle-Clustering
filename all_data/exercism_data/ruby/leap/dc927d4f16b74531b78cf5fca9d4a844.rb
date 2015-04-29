class Year
  class << self
    def leap?(year)
      @year = year
      fourth? && (not_century? || fourth_century?)
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

    def fourth_century?
      nth?(400)
    end

    def nth?(n)
      (@year % n).zero?
    end
  end
end
