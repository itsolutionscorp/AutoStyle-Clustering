class Proverb
  def initialize(*l, **h)
    @l, @h = l, h
  end

  def to_s
    res = ""
    @l.each_cons(2) { |w1, w2| res << "For want of a #{w1} the #{w2} was lost.\n" }
    res << "And all for the want of a "
    q = @h[:qualifier]
    res << "#{q} " if q
    res << "#{@l[0]}."
  end
end
