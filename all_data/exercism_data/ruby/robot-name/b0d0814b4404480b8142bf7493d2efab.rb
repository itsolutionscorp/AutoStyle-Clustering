class Robot

  attr_reader :name
  @@invalid_names = []

  def initialize
    @name = ('A'..'Z').to_a.sample(2).join + rand(100..999).to_s
    @@invalid_names.include?(@name) ? intialize : @@invalid_names << @name
    @name
  end

  def reset
    initialize
  end

end
