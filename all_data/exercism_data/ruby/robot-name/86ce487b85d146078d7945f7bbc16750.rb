class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = ""
    2.times { @name << ("A".."Z").to_a.sample }
    3.times { @name << ("0".."9").to_a.sample }
  end
end
