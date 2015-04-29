class Robot
  attr_reader :name
  def initialize()
    @my_name = (0...2).map { (65 + rand(26)).chr }.join + rand(100...999).to_s
  end
  
  def name
    @my_name
  end
  
  def reset
   initialize
  end
end

puts Robot.new.name
