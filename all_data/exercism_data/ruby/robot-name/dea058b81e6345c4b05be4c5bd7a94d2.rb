class Robot

  def initialize
    @name = ""
  end
  
  def name
    2.times { @name << (65 + rand(26)).chr }
    @name << rand(100..999).to_s
  end

  def reset
    @name = ""
  end
  
end
