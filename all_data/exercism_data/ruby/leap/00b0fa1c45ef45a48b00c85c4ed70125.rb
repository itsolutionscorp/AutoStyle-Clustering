module Year
  def self.leap?(year)
    @year = year
    if divisible_by_4?
      if divisible_by_100?
        if divisible_400?
          return true
        end
        return false
      end
      return true
    end
  end

  private

  def self.divisible_by_4?
    @year.modulo(4) == 0
  end

  def self.divisible_by_100?
    @year.modulo(100) == 0
  end

  def self.divisible_400?
    @year.modulo(400) == 0
  end
end
