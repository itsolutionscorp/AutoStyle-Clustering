class Proverb
  attr_reader :consequences

  def initialize(*consequences)
    @consequences = consequences
  end

  def to_s
    chorus + last_line 
  end

  def chorus
    lines = []
    hash_pairs(*consequences).each_pair do |k, v|
      lines << "For want of a #{k} the #{v} was lost.\n" 
    end
    lines.join
  end

  def last_line
    if @consequences.last.is_a?(Hash)
      h = @consequences.pop
    end
    if h.nil?
      "And all for the want of a #{consequences[0]}."
    else
      "And all for the want of a #{h[:qualifier]} #{consequences[0]}."
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
