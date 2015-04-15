class Robot
  attr_accessor :name

  def initialize
    @name = set_name!
  end

  def reset
    set_name!
  end


  private

  def set_name!
    @name = (0...2).map { (65 + rand(26)).chr }.join
    @name << (0...3).map { rand(9)}.join
  end

end
