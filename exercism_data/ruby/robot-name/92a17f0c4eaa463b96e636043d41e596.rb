class Robot
  attr_accessor :name 
  @@names_in_use = []

  def initialize
    random_letters = ("a".."z").to_a.shuffle[0..1].join
    random_numbers = rand(1000...10000).to_s 
    self.name = random_letters + random_numbers
    reset if @@names_in_use.include?(name) == true #reset if name in use
    @@names_in_use << self.name
     
  end

  def reset
    initialize() 
  end

end
