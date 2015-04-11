class Proverb

  def initialize(*items, qualifier: "")
    @items = items
    @qualifier = qualifier
  end

  def to_s
    lines.join("\n")
  end

  private

  attr_reader :items, :qualifier

  def lines
    standard_lines << final_line
  end

  def standard_lines
    items.each_cons(2).map { |(item1, item2)| line(item1, item2) }
  end

  def line(item1, item2)
    "For want of a #{item1} the #{item2} was lost."
  end

  def final_line
    "And all for the want of a #{ultimate_cause}."
  end

  def ultimate_cause
    "#{qualifier} #{items.first}".lstrip
  end

end
