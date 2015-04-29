class Robot
  attr_accessor :name

  @@prev_name = "AA000"

  def initialize
    reset
  end

  def reset 
    @name = @@prev_name.next!.dup
    true
  end
end
