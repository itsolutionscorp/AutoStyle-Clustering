class Raindrops
  def self.convert(drops)
    drops = Divisible_by.new(drops)
    case
    when drops.by_3? && drops.by_5? && drops.by_7? then "PlingPlangPlong"
    when drops.by_3? && drops.by_5?                then "PlingPlang"
    when drops.by_3? && drops.by_7?                then "PlingPlong"
    when drops.by_5? && drops.by_7?                then "PlangPlong"
    when drops.by_3?                               then "Pling"
    when drops.by_5?                               then "Plang"
    when drops.by_7?                               then "Plong"
    else
      drops.drops.to_s
    end
  end
end

class Divisible_by
  attr_reader :drops

  def initialize(drops)
    @drops = drops
  end

  def by_3?
    drops % 3 == 0
  end

  def by_5?
    drops % 5 == 0
  end

  def by_7?
    drops % 7 == 0
  end
end
