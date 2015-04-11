class Robot
  def initialize
    self.newname
  end

  def name
    @robotname
  end

  def reset
    self.newname
  end

  def newname
    @robotname = (0...2).map { (65+rand(26)).chr }.join + (0...3).map { (rand(9)) }.join
  end

end
