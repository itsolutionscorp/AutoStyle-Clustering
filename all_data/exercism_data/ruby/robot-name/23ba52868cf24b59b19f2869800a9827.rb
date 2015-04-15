require 'set'

class Robot
  @@used_names = Set.new

  def initialize
    tmp_name = uniq_name
    @@used_names << tmp_name
    @robot_name = tmp_name
  end
    
  def make_name
    tmp_name = ('A'..'Z').to_a.sample(2).join
    tmp_name << (0..9).to_a.sample(3).join
    tmp_name
  end
  
  def uniq_name
    tmp_name = make_name
    while @@used_names.member?(tmp_name) 
      tmp_name = make_name
    end
    tmp_name
  end

  def name
    @robot_name
  end

  def reset
    @robot_name = uniq_name
  end
end
