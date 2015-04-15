class Robot
  attr_reader :name
  
  def initialize
    @name = randomly_generate_name
  end
  
  def reset
    @name = randomly_generate_name
  end
  
  private
  
  def randomly_generate_name
    (0...2).map { (65 + rand(26)).chr }.join + rand(100..1000).to_s
  end
end
