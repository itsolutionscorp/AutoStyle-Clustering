class Robot
  attr_accessor :name
    
  def initialize
    @name = create_new_name
  end

  def create_new_name
    prefix = (Array 'A'..'Z').shuffle[0,2].join("")
    suffix = (Array '0'..'9').shuffle[0,3].join("")
    prefix + suffix
  end

  def reset
    @name = create_new_name
  end
end
