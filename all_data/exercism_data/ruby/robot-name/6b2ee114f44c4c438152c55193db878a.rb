class Robot

  attr_accessor :name

  Alphabet, Numbers = ("A".."Z").to_a, ("0".."9").to_a
  Names = []

  def initialize
    @name = name_generator
    Names << name
  end

  def name_generator
    generated_name = Alphabet.sample(2).concat(Numbers.sample(3)).join("")
    generated_name = name_generator if Names.include? generated_name
    generated_name
  end

  def reset
    self.name = name_generator
  end

end
