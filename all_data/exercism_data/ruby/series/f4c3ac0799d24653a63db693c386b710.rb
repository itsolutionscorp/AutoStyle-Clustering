class Series
  attr_accessor :s

  def initialize s
    self.s = s.chars.map(&:to_i)
  end

  def slices n
    # ugh. too prescriptive "you deserve whatever you get" means it is up to me.
    raise ArgumentError if n > s.size
    s.each_cons(n).map(&:to_a)
  end
end
