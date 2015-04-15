class Proverb
  
  def initialize(*chain)
    @chain = chain    
  end  
  
  def optional_qualifier?( consequence )
    consequence.is_a?(Hash)
  end 
  
  def final_consequence
    @chain.last[:qualifier]+ " " if optional_qualifier?( @chain.last )
  end   
  
  def final_sentence
    "And all for the want of a #{final_consequence}#{@chain.first}."
  end  
  
  def any_sentence(prev_consequence, consequence)
    "For want of a #{prev_consequence} the #{consequence} was lost.\n"
  end  
  
  
  def to_s
  @the_proverb = ""  
  @chain.each_cons(2) do |pair|
    @the_proverb << any_sentence(pair.first, pair.last) unless optional_qualifier?( pair.last )
  end
  @the_proverb << final_sentence
  end  
  
end  
