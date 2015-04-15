class Robot
  @@letters = ('A'..'Z').to_a
  @@numbers = ('0'..'9').to_a
  @@name_chars = [@@letters, @@letters, @@numbers, @@numbers, @@numbers]
  
  def initialize
    @name = ''
  end
  
  def name
    if @name.empty?
      @name = @@name_chars.map{|a| a.sample}.join
    end
    
    @name
  end
  
  def reset
    @name = ''
  end
end
