class Robot
  NAME_LENGTH = 6
  attr_reader :name
  
  def initialize
    reset
  end
  
  def reset
    generate_name
  end
  
  def generate_name
    @name = Array.new(NAME_LENGTH){[*"A".."Z", *"0".."9"].sample}.join
  end
end
