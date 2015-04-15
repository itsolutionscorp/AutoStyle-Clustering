class Raindrops

  def self.convert(drop)
    if (factor_3(drop).nil? && factor_5(drop).nil? && factor_7(drop).nil?)
      return drop.to_s
    else
      first = unless factor_3(drop).nil? then factor_3(drop) end
      second = unless factor_5(drop).nil? then factor_5(drop) end
      third = unless factor_7(drop).nil? then factor_7(drop) end
    end

    (first || "") + (second || "") + (third || "")
  end

  def self.factor_3(num)
    if num%3 == 0 then "Pling" end
  end

  def self.factor_5(num)
    if num%5 == 0 then "Plang" end
  end

  def self.factor_7(num)
    if num%7 == 0 then "Plong" end
  end
end

# Not a very elegant solution...but it works
