class Robot
  attr_accessor :name, :output
  @@store = []
  @output = ''

  def name
    if @@store.include?(@output)
      @output
    else
      namegen
    end
  end

  def namegen
    output = ''
    output << ('a'..'z').to_a.sample(2).join
    output << ('0'..'9').to_a.sample(3).join
    @@store << output
    @output = output
    @output
  end

  def reset
    @@store.delete(@output)
  end
end

# robot = Robot.new
# puts robot.name
