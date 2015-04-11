class Robot
  def initialize
    @name=new_name
  end

  def reset
    @name=new_name
  end

  def name
    @name
  end

  def new_name
    name=(0..1).map {(rand(26)+65).chr}.join
    name+=(0..3).map {(rand(10)+48).chr}.join
  end

end
