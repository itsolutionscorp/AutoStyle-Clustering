class Raindrops
  def self.convert(n)
    return n.to_s unless n % 3 == 0 || n % 5 == 0 || n % 7 == 0 
    "#{self.pling(n)}#{self.plang(n)}#{self.plong(n)}"
  end

  private
    def self.pling(n)
      "Pling" if n % 3 == 0
    end

    def self.plang(n)
      "Plang" if n % 5 == 0
    end

    def self.plong(n)
      "Plong" if n % 7 == 0
    end
end
