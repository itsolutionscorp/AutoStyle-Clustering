class Proverb
  def initialize(*the_lost, qualifier: nil)
    @the_lost = the_lost
    @qualifier = qualifier
  end

  def to_s
    [*verses, terminal_verse].join "\n"
  end

  private

  attr_reader :the_lost, :qualifier

  def verses
    the_lost.each_cons(2).map { |cause, effect| verse cause, effect }
  end

  def verse cause, effect
    "For want of a #{cause} the #{effect} was lost."
  end

  def terminal_verse
    "And all for the want of a #{qualifier} #{circular_cause}.".squeeze ' '
  end

  def circular_cause
    the_lost.first
  end
end
