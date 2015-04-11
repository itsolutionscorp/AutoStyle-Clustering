class Robot

  def initialize

  end

  def name
    @robot = Robot.new

    @robot = [*'a'..'z', *'A'..'Z'].sample + [*'a'..'z', *'A'..'Z'].sample \
          + [*0..9].sample.to_s + [*0..9].sample.to_s + [*0..9].sample.to_s
    # puts "name is #{@robot}"

    return @robot

  end

  def reset
  end
end
