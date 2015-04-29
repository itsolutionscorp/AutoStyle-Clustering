class Robot
  def name
    @name ||= "#{letters}#{numbers}"
  end

  def reset
    @name = nil
  end

  def letters
    options = ('A'..'Z').to_a
    options.sample(2).join("")
  end

  def numbers
    rand(100...999)
  end
end
