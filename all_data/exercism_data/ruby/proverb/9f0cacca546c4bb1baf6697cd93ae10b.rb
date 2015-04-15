class Proverb
  def initialize(*words)
    @words = words
    if Hash === words.last && !words.last[:qualifier].to_s.empty?
      @qualifier = words.last[:qualifier] + ' '
      @words.pop
    end
  end

  def to_s
    @words
      .each_cons(2)
      .map { |a, b|  "For want of a #{a} the #{b} was lost.\n" }
      .join +
      "And all for the want of a #{@qualifier}#{@words.first}."
  end
end
