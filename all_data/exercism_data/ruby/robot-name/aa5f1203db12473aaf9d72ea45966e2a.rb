class Robot
  def initialize
    @name = new_name
  end
  
  def name
    return @name
  end
  
  def reset
    @name = new_name
  end
  
  private
  
  def new_name
    (1..2).map { ('A'..'Z').to_a[Random.new.rand(26)] }.join +
	Random.new.rand(100..999).to_s
  end
end
