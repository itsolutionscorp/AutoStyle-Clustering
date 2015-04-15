class Robot

  def initialize
  	@name = (0...2).map{ ('a'..'z').to_a[rand(26)] }.join
  	3.times {@name << rand(10).to_s}
  	@name
  end

  def name
  	@name
  end

  def reset
  	initialize
  end
  
end
