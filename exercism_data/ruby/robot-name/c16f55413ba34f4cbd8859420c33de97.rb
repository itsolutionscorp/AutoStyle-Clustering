class Robot
  @@names= {}
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
    begin
      name=(0..1).map {(rand(26)+65).chr}.join
      name+=(0..3).map {(rand(10)+48).chr}.join
    end while @@names[name]
    @@names[name]=true
    name
  end

end
