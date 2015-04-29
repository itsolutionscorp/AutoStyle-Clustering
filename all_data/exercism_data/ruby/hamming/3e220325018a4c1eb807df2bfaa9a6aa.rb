class Hamming
  def self.compute(x, y)
    x.chars.zip(y.chars).map {|x, y| count(x, y)}.inject(:+)
  end

  def self.count(x, y)
    if x.nil? or y.nil?
      0
    else
      to_enum(x != y)
    end
  end

  def self.to_enum(t)
    if t
      1
    else
      0
    end
  end
end
