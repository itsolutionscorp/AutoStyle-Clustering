class Proverb
  def initialize(*the_lost, qualifier: nil)
    @the_lost = the_lost
    @qualifier = "#{qualifier} " if qualifier
  end

  def to_s
    verse
  end

  private

  attr_reader :the_lost, :qualifier

  def verse
    body + ending
  end

  def body
    the_lost.each_cons(2).map { |cause, effect| line cause, effect }.join
  end

  def ending
    "And all for the want of a #{qualifier}#{the_lost.first}."
  end

  def line cause, effect
    "For want of a #{cause} the #{effect} was lost.\n"
  end
end
