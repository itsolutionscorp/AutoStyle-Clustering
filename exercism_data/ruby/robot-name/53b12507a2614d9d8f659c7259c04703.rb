class Robot
  
  def initialize
    @name = self.class.generate_unique_name
  end

  def name
    @name   
  end

  def reset
    initialize
  end
  

private

  @names = []

  def self.generate_unique_name
    begin
      new_name = generate_name
    end while @names.include? new_name

    @names << new_name 
    new_name
  end

  def self.generate_name
    letters =  ('A'..'Z').to_a.sample(2).join 
    numbers = rand(1000).to_s.rjust(3, '0') 

    "#{ letters }#{ numbers }"
  end

end
