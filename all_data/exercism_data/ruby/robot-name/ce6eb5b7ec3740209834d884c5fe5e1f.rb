class Robot
  attr_accessor :name
  
  @@prefixes = ('A'..'Z').to_a
  @@postfixes = ('0'..'9').to_a

  def initialize
    @used_names = [nil]
    set_name    
  end

  def reset
    set_name
  end
  
  private

  def set_name
    while @used_names.include? name do
      self.name = prefix + postfix
    end 
    @used_names << name
  end

  def prefix
    @@prefixes.sample(2).join
  end

  def postfix
    @@postfixes.sample(3).join
  end

end
