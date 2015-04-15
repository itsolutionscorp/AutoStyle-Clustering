class Robot

  attr_accessor :name
  def name 
    @name || _name
  end

  def _name
    letters = (0..1).map { (65+rand(26)).chr }.join
    numbers = (0..2).map { rand(10).to_s }.join
    @name = letters + numbers
  end

  def reset
    _name
  end

end
