class Proverb
  def initialize(*missing_things, qualifier: nil)
    @missing_things = missing_things
    @first_wanted = @missing_things[0]
    @qualifier = qualifier
  end

  def to_s
    @proverb ||= make_proverb
  end

  private

  def make_proverb
    @missing_things.each_cons(2).map { |wanted, lost|
      line_from(wanted, lost)
    }.push(last_line).join
  end

  def line_from(wanted, lost)
    "For want of a #{wanted} the #{lost} was lost.\n"
  end

  def last_line
    "And all for the want of a #{@qualifier} #{@first_wanted}.".squeeze(" ")
  end
end
