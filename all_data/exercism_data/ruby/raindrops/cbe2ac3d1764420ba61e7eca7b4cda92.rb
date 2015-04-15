class Raindrops
  class Roof
    def initialize(number)
      @number = number
      @o      = ''
    end

    def plong
      @o += "Plong" if @number % 7 == 0
      self
    end

    def plang
      @o += "Plang" if @number % 5 == 0
      self
    end

    def pling
      @o += "Pling" if @number % 3 == 0
      self
    end

    def to_s
      (@o.empty? ? @number : @o).to_s
    end
  end

  def self.convert(number)
    Roof.new(number).tap do |roof|
      roof.pling.plang.plong
    end.to_s
  end
end
