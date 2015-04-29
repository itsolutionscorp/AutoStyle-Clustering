class Proverb

  def initialize(*things, qualifier: "")
    @things = things
    @qualifier = qualifier
  end

  def to_s
    lines.join("\n")
  end

  private

  attr_reader :things, :qualifier

  def lines
    standard_lines << final_line
  end

  def standard_lines
    things.each_cons(2).map { |(smaller, larger)| line(smaller, larger) }
  end

  def line(smaller_thing, larger_thing)
    "For want of a #{smaller_thing} the #{larger_thing} was lost."
  end

  def final_line
    "And all for the want of a #{smallest_thing}."
  end

  def smallest_thing
    "#{qualifier} #{things.first}".lstrip
  end

end
