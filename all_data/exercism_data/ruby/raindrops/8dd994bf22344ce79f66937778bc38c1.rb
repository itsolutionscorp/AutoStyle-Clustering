class Raindrops
  def self.convert(n)
    Storm.new(n).drop(3, "Pling").drop(5, "Plang").drop(7, "Plong").rain();
  end

  class Storm
    def initialize(n)
      @n   = n
      @str = ""
    end

    def drop(m, str)
      if @n % m == 0
        @str += str
      end

      self
    end

    def rain
      @str == "" ? @n.to_s : @str
    end
  end
end
