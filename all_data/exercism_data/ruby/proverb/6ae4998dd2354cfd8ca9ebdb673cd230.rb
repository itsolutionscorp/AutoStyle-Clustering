class Proverb

  def initialize(*consequences, qualifier: nil)
    @consequences = consequences
    @modifier = qualifier
  end

  def to_s
    chorus + last_line 
  end

  private
  attr_reader :consequences, :modifier

  def chorus
    consequences.each_cons(2).collect do |old_thing, new_thing|
      "For want of a #{old_thing} the #{new_thing} was lost.\n"
    end.join
  end

  def last_line
    "And all for the want of a #{adjective.join(' ')}."
  end

  def adjective
    [modifier, consequences.first].compact
  end
end
