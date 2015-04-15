class Raindrops
  def self.convert(x)
    result = ''
    special = false

    if entire_division?(x, 3)
      result = "#{result}Pling"
      special = true
    end

    if entire_division?(x, 5)
      result = "#{result}Plang"
      special = true
    end

    if entire_division?(x, 7)
      result = "#{result}Plong"
      special = true
    end

    if !special
      result = x.to_s
    end

    result
  end

  private

  def self.entire_division?(a, b)
    a % b == 0
  end
end
