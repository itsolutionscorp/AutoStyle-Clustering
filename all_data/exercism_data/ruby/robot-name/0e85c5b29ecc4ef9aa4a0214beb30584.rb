class Robot

  attr_reader :name

  def initialize 
    create_name
  end

  def reset
    create_name
  end

  def create_name
    letter  = ('AA'..'ZZ').to_a.sample
    number  = rand(1000)
    
    @name = "#{letter}#{number}"
  end

end
