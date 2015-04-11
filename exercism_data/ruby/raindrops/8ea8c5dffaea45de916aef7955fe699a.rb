class Raindrops
  def self.convert(number)
    @o = ''

    plong plang pling number

    result = @o.empty? ? number : @o

    @o = ''
    result.to_s
  end

  class << self
    def plong(number)
      @o += "Plong" if number % 7 == 0
      number
    end

    def plang(number)
      @o += "Plang" if number % 5 == 0
      number
    end

    def pling(number)
      @o = "Pling"  if number % 3 == 0
      number
    end
  end
end
