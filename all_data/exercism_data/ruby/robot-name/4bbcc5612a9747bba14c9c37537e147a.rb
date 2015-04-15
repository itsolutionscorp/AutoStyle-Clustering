class Robot

  attr_reader :two_letter_identfier, :three_number_identifier

  def initialize
    @two_letter_identifier = (0...2).map { ('A'..'Z').to_a[rand(26)] }.join
    @three_number_identifier = (0...3).map { ('0'..'9').to_a[rand(10)] }.join
    @robot_name = @two_letter_identifier + @three_number_identifier
  end

  def name
    @robot_name.to_s
  end

  def reset
    @robot_name = Robot.new
  end

end
