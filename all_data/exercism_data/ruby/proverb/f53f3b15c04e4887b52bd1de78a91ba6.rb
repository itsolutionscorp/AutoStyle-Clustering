class Proverb
  attr_reader :consequences

  def initialize(*consequences, qualifier: nil)
    @consequences = consequences
    @modifier = qualifier
  end

  def to_s
    chorus + last_line 
  end

  def chorus
    lines = []
    hash_pairs(*consequences).each_pair do |old_thing, new_thing|
      lines << "For want of a #{old_thing} the #{new_thing} was lost.\n" 
    end
    lines.join
  end

  def last_line
    if @modifier == nil
      "And all for the want of a #{consequences[0]}."
    else
      "And all for the want of a #{@modifier} #{consequences[0]}."
    end
  end

  private

  def hash_pairs(*consequences)
    clean_array = consequences.select{|e| e.is_a?(String)}
    array = []
    clean_array.each_cons(2) {|pair| array << pair}
    Hash[array]
  end
end
