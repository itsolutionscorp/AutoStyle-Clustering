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
  @ret_val = ""  
  @chain.each_with_index do |consequence, index|
    @ret_val << any_sentence(@chain[index-1], consequence) unless index == 0 || optional_qualifier?( consequence )
  end
  @ret_val << final_sentence
  end  
  
end  
