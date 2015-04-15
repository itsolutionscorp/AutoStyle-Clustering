class Robot
  attr_reader :name

  def initialize
    @history_name = []
    set_name
  end
  
  def reset
    set_name
  end

private
  def create_name
    random_name = [*'A'..'Z'].sample(2).join + [*0..9].sample(3).join 
 
    if (@history_name.include?(random_name))
      create_name
    else
      random_name
    end

  end

  def set_name
    @name = create_name
    @history_name.push(@name)
  end

end
