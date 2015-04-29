class Robot
  attr_accessor :name

  def initialize
    @name = reset
  end

  def reset
    self.name = "#{(0...2).map{ (65 + rand(26)).chr }.join}#{'%.03d' % Random.rand(999)}"
  end

end
