class Proverb
  
  def initialize *chain
    @options = chain.last.is_a?(Hash) ? chain.pop : {}
    @chain = chain
    @line = "For want of a %s the %s was lost."
    @conclusion = "And all for the want of a %s."
  end
  
  def to_s
    chain_of_events + conclusion
  end
  
  private
  
  def chain_of_events
    @chain.each_cons(2).with_object([]) do |(wanted_item, lost_item), lines|
      lines << (@line % [wanted_item, lost_item])
    end.join("\n")
  end
  
  def conclusion
     "\n" + (@conclusion % [@options[:qualifier], @chain.first].compact.join(' '))
  end
  
end
