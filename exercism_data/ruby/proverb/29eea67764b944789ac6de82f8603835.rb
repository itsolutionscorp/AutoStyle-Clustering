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
    chorus =  consequence_pairs.collect do |old_thing, new_thing|
      "For want of a #{old_thing} the #{new_thing} was lost.\n"
    end
    chorus.join
  end

  def last_line
    original_thing = [modifier, consequences.first].compact
    "And all for the want of a #{original_thing.join(' ')}."
  end

  def consequence_pairs
    consequences.each_cons(2).collect {|pair| pair}
  end
end
