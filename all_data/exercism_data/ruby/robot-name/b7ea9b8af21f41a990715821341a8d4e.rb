class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = ""
    
    2.times { @name << (?A..?Z).to_a.sample }
    3.times { @name << rand(10).to_s }
  end

end
