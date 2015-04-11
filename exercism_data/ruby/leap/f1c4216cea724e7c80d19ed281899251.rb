class Year
  class << self
    def leap?(year)
      quadricentennial?(year) || divisible?(year, 4) && !centennial?(year)
    end

  private

    def centennial?(year)
      divisible?(year, 100)
    end

    def divisible?(dividend, divisor)
      dividend % divisor == 0
    end

    def quadricentennial?(year)
      divisible?(year, 400)
    end
  end
end
