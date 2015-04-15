require 'pry'
class Proverb

  def initialize(*consequences, qualifier: nil)
    @consequences = consequences
    @modifier = qualifier
  end

  def to_s
    chorus + last_line 
  end

  private

  attr_reader :consequences

  def chorus
    lines = []
    hash_pairs.each do |old_thing, new_thing|
      lines << "For want of a #{old_thing} the #{new_thing} was lost.\n" 
    end
    lines.join
  end

  def last_line
    original_thing = [consequences[0]]
    original_thing.unshift(@modifier) if @modifier
    "And all for the want of a #{original_thing.join(' ')}."
  end

  def hash_pairs
    pairs_consequences = []
    @consequences.each_cons(2) {|pair| pairs_consequences << pair}
    pairs_consequences
  end
end
