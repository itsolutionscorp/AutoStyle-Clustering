class Robot
  attr_reader :name

  def initialize
    @name = rand(65..90).chr + rand(65..90).chr + "%03d" % rand(999)
  end

  def reset 
    initialize
  end
end
