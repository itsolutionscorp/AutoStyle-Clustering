class Proverb
  def initialize (*ss)
    dict = ss.last.is_a?(Hash) ? ss.pop : {}
    @q = dict[:qualifier]
    @ss = ss
  end
  def to_s
    (@ss.each_cons(2).map{|a, b|
    "For want of a #{a} the #{b} was lost."
    } + ["And all for the want of a #{@q ? @q + " " : ""}#{@ss.first}."]).join("\n")
  end
end
