class Year
  class << self
    @@year
    def leap?(year)
      @@year = year
      if by_100?
        by_400? ? true : false
      else
        by_4?
      end
    end

    private

    def by_4?
      ((@@year / 4.0) % 1 == 0) ? true : false
    end

    def by_100?
      ((@@year / 100.0) % 1 == 0) ? true : false
    end

    def by_400?
      ((@@year / 400.0) % 1 == 0) ? true : false
    end
  end
end
