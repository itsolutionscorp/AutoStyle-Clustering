class Robot
  LETTERS = ('A'..'Z').to_a
  DIGITS  = ('0'..'9').to_a

  @@used_names = []

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @@used_names.delete(@name)
    loop do
      @name = (LETTERS.sample(2) + DIGITS.sample(3)).join
      break unless @@used_names.include? @name
    end
    @@used_names << @name
    @name
  end
end
