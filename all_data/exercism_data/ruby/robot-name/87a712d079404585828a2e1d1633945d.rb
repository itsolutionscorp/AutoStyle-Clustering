class Robot
  attr_reader :name
  
  def initialize
    @used_names = []
    @name = pick_name
  end
  
  def reset
    @name = pick_name
  end
  
  def pick_name
    begin 
      @name = generate_name
    end while @used_names.include? @name
    @used_names << @name
	@name
  end
  
  def generate_name
    (1..2).map { random_letter }.join + (1..3).map { random_digit }.join
  end
  
  def random_letter
    ('A'..'Z').to_a[rand(26)]
  end
  
  def random_digit
    rand(9)
  end
end
