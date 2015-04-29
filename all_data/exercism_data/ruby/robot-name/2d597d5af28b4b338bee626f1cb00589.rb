class Robot
  @@names = []
  attr_accessor :name

  def initialize
    create_name
  end

  def reset
    create_name
  end

  def create_name
    @name = nil

    while !@name
     generated_name = "#{r_letter}#{r_letter}#{r_number}#{r_number}#{r_number}"
     @name = generated_name unless @@names.include?(generated_name)
    end
  end

  def r_letter
    ("A".."Z").to_a.sample
  end

  def r_number
    (0..9).to_a.sample
  end
end
