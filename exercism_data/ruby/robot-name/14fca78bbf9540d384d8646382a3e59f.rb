class Robot
  attr_accessor :name
  
  def initialize
    @name = namerize
  end

  def namerize
    name = ''
    name << (0...2).map { (65 + rand(26)).chr }.join
    name << (0...3).map { rand(10) }.join
    name
  end

  def reset
    @name = namerize
  end
end
