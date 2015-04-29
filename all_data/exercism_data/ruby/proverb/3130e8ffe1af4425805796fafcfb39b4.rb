class Proverb
  
  def initialize *chain
    @options = chain.last.is_a?(Hash) ? chain.pop : {}
    @chain = chain
  end
  
  def to_s
    chain_of_consequences + conclusion
  end
  
  private
  
  def chain_of_consequences
    wanted_and_lost.with_object("") do |(wanted_item, lost_item), mounting_consequences|
      mounting_consequences << consequence(wanted_item, lost_item)
    end
  end
  
  def consequence wanted_item, lost_item
    line % [wanted_item, lost_item]
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
