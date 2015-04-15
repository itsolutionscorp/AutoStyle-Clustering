# class Robot
class Robot
  attr_accessor :name
  @@counter = 0
  def initialize
    get_name
  end

  def reset
    get_name
  end

  def get_name
    @name = "CHITTI#{"%03d" % @@counter}"
    @@counter += 1
  end
end
