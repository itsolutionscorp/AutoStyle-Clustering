class Robot

  attr_accessor :name

  # Generate sequence [a,...,z,A,...,Z]   
  @@letters = []
  (1..26).inject('a') { |current_letter, _| @@letters << current_letter; current_letter = current_letter.succ}
  (1..26).inject('A') { |current_letter, _| @@letters << current_letter; current_letter = current_letter.succ}

  def initialize
    new_name
  end

  def reset
    new_name
  end

  def new_name
    # Name must match regexp /^[a-zA-Z]{2}\d{3}$/ ie 2 letters followed by 3 digits
    @name = ''
    2.times do
      @name << @@letters[Random.rand(@@letters.size)]
    end
    3.times do
      @name << Random.rand(10).to_s
    end
  end


end
