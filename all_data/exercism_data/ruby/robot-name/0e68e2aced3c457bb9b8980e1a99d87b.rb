class Robot

  attr_accessor :name
  @@letters = ('a'..'z').to_a + ('A'..'Z').to_a

  def initialize
    new_name
  end

  def reset
    new_name
  end

  def new_name
    # Name must match regexp /^[a-zA-Z]{2}\d{3}$/ ie 2 letters followed by 3 digits
    @name = ''
    2.times { @name << @@letters[Random.rand(@@letters.size)] }
    3.times { @name << Random.rand(10).to_s }
  end


end
