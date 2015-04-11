class Proverb
  def initialize(*chain, qualifier: "")
    @chain = chain
    @qualifier = qualifier
  end
  
  def to_s
    proverb = ""
    chain.each_with_index do |cause, index|
      if cons = chain[index+1]
        proverb << "For want of a #{cause} the #{cons} was lost.\n"
      end
    end
    proverb << "And all for the want of a #{qualifier}#{chain.first}."
  end
  
  private
  
  def chain
    @chain
  end
  
  def qualifier
    unless @qualifier.empty?
      @qualifier+" "
    end
  end
end
