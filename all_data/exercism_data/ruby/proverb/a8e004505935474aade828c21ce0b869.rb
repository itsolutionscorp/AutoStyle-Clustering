class Proverb
  
  def initialize *chain
    @options = chain.last.is_a?(Hash) ? chain.pop : {}
    @chain = chain
  end
  
  def to_s
    lines + conclusion
  end
  
  private
  
  def lines
    wanted_and_lost.with_object("") do |(wanted_item, lost_item), lines|
      lines << (line % [wanted_item, lost_item])
    end
  end
  
  def wanted_and_lost
    @chain.each_cons(2)
  end
  
  def line
    "For want of a %s the %s was lost.\n"
  end
  
  def last_line
    "And all for the want of a %s."
  end
  
  def conclusion
    (last_line % [@options[:qualifier], @chain.first].compact.join(' '))
  end
  
end
