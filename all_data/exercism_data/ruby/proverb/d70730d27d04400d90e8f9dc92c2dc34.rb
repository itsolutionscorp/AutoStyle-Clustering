class Proverb
  
  def initialize *chain
    @options = chain.last.is_a?(Hash) ? chain.pop : {}
    @chain = chain
    @line = "For want of a %s the %s was lost.\n"
    @conclusion = "And all for the want of a %s."
  end
  
  def to_s
    chain_of_events + conclusion
  end
  
  private
  
  def chain_of_events
    @chain.each_cons(2).with_object("") do |(wanted_item, lost_item), events|
      events << (@line % [wanted_item, lost_item])
    end
  end
  
  def conclusion
    (@conclusion % [@options[:qualifier], @chain.first].compact.join(' '))
  end
  
end
