class Robot
  attr_accessor :name
  
  def initialize
    @name = random_name
  end  
  
  def reset
    @name = random_name
  end
  
  private
  def random_name
    [*'a'..'z', *'A'..'Z'].shuffle.first(2).join + rand(000..999).to_s
  end  
    
end  
