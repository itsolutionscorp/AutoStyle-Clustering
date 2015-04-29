class Raindrops
  def self.convert(num)
    Raindrops.new(num).sound
  end

  attr_reader :num

  def initialize(num)
    @num = num
  end

  def sound
    !rain.empty? ? rain : num.to_s
  end

  private
    def rain
      "#{pling}#{plang}#{plong}"
    end

    def pling
      "Pling" if num % 3 == 0
    end

    def plang
      "Plang" if num % 5 == 0
    end

    def plong
      "Plong" if num % 7 == 0
    end
end
