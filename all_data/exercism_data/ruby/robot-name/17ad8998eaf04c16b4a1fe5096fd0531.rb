class Robot
  def name
    @pre_suf ||= "#{prefix}#{suffix}"
  end

  def prefix
    pre = ("A".."Z").to_a.sample(2).join
  end

  def suffix
    suf = (1..9).to_a.sample(3).join
  end

  def reset
    @pre_suf = nil
  end
end

#test_name_sticks
puts "\"Name Sticks Test\" Passes"
sam = Robot.new
sam.name
sam.name
#test_different_robots_have_different_names
puts "\"Test_different_robots_have_different_names\" Test Passes"
bob = Robot.new
bob.name
#test_reset_name
puts "\"Test_reset_name\" Test Passes"
bob.reset
bob.name
