class Proverb
  def initialize(*the_lost, qualifier: nil)
    @the_lost = the_lost
    @qualifier = qualifier
  end

  def to_s
    [*lines, terminal_line].join "\n"
  end

  private

  attr_reader :the_lost, :qualifier

  def lines
    the_lost.each_cons(2).map { |cause, effect| verse cause, effect }
  end

  def terminal_line
    "And all for the want of a #{qualifier} #{circular_cause}.".squeeze ' '
  end

  def verse cause, effect
    "For want of a #{cause} the #{effect} was lost."
  end

  def circular_cause
    the_lost.first
  end
end
