class Raindrops
  class << self
    def convert(n)
      drought?(n) ? "#{n}" : rainfall(n)
    end

  private

    def divisible?(dividend, divisor)
      dividend % divisor == 0
    end

    def drought?(n)
      rainfall(n).empty?
    end

    def raindrops
      { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    end

    def rainfall(n)
      raindrops.map { |divisor, sound| sound if divisible?(n, divisor) }.join
    end
  end
end
