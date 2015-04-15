class Proverb
  
  def initialize(*chain)
    @chain = chain
    @qualifier =@chain[@chain.length-1][:qualifier] +" " if @chain[@chain.length-1].is_a?(Hash)
  end  
  
  def to_s
  @ret_val = ""  
  @chain.each_with_index do |item, index|
    @ret_val += "For want of a "+@chain[index-1]+" the "+item+" was lost.\n" if index > 0 && item.is_a?(String) 
  end
  @ret_val +="And all for the want of a #{@qualifier}#{@chain[0]}."
  end  
  
end  
