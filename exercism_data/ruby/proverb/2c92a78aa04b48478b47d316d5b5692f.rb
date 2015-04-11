class Proverb
  def initialize(*missing_things, qualifier: nil)
    @missing_things = missing_things
    @qualifier = qualifier
  end

  def to_s
    @proverb ||= make_proverb
  end

  private

  def make_proverb
    @missing_things.each_cons(2).map { |wanted, lost|
      line_from(wanted, lost)
    }.join << last_line
  end

  def line_from(wanted, lost)
    "For want of a #{wanted} the #{lost} was lost.\n"
  end

  def last_line
    "And all for the want of a #{@qualifier} #{@missing_things[0]}.".squeeze(" ")
  end
end
