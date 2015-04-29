class Proverb

  def initialize(*consequences, qualifier: nil)
    @consequences = consequences
    @modifier = qualifier
    compute_last_line
  end

  def to_s
    chorus + last_line 
  end

  private

  def chorus
    lines = []
    hash_pairs(*consequences).each_pair do |old_thing, new_thing|
      lines << "For want of a #{old_thing} the #{new_thing} was lost.\n" 
    end
    lines.join
  end

  attr_reader :last_line
  attr_reader :consequences

  def compute_last_line
    last = [consequences[0]]
    last.unshift(@modifier) if @modifier
    @last_line = "And all for the want of a #{last.join(' ')}."
  end

  def hash_pairs(*consequences)
    clean_array = consequences.select{|e| e.is_a?(String)}
    array = []
    clean_array.each_cons(2) {|pair| array << pair}
    Hash[array]
  end
end
