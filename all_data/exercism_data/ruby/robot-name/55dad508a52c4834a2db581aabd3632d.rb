class Robot
  @@count = 1
  attr_accessor :name
  def initialize
    reset
  end
  def reset
    @name = "AB" + @@count.to_s.rjust(3, "0")
    @@count += @@count
  end
end
