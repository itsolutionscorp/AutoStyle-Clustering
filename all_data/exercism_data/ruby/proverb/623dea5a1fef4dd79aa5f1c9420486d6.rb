class Proverb
  def initialize(*things, qualifier: nil)
    @things = things
    @qualifier = qualifier
  end

  def to_s
    lines = things.each_cons(2).map do |thing, other_thing|
      "For want of a #{thing} the #{other_thing} was lost."
    end
    lines << "And all for the want of a #{first_thing}."
    lines.join("\n")
  end

  private

  attr_reader :things, :qualifier

  def first_thing
    [qualifier, things.first].compact.join(" ")
  end
end
