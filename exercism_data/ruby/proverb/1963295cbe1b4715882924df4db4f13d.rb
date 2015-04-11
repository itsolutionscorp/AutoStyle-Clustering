class Proverb
  def initialize(*missing_things, qualifier: nil)
    @missing_things = missing_things
    @qualifier = qualifier + " " if qualifier
  end

  def to_s
    @proverb ||= make_proverb
  end

  private
  def make_proverb
    (0...@missing_things.length - 1).map { |i|
        line_from @missing_things[i], @missing_things[i + 1]
    }.join << last_line
  end

  def line_from(wanted, lost)
    "For want of a #{wanted} the #{lost} was lost.\n"
  end

  def last_line
    "And all for the want of a #{@qualifier}#{@missing_things[0]}."
  end
end
