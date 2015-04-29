class Robot
  attr_accessor :name
  
  PREFIX_CHARS = ('A'..'Z').to_a
  POSTFIX_CHARS = ('0'..'9').to_a

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
    Array.new(2).map { PREFIX_CHARS.sample }.join    
  end

  def postfix
    Array.new(3).map { POSTFIX_CHARS.sample }.join
  end

end
