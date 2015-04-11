module Year
  class << self
    def leap? year
      return true if divisble? year, 400
      return false if divisble? year, 100
      divisble? year, 4
    end

    private

    def divisble?(n, divisor)
      n % divisor == 0
    end
  end
end
