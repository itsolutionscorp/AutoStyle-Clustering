class Robot

  CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  
  def initialize
    @name = name
  end

  def name
    name = ""
    2.times {name << CHARACTERS[rand(26)]} + 3.times {name << rand(10).to_s}
    @name ||= name
  end

  def reset
    @name = nil
    name
  end

end
