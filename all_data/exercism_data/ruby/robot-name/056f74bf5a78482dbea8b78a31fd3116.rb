class Robot
  attr_reader :name

  LETTERS = ('A'..'Z').to_a + ('a'..'z').to_a
  NUMBERS = (0..9).to_a

  @@used_names = []
  def initialize
    reset
  end

  def reset
    @name = generate_name
    @@used_names << @name
  end

  def self.used_names
    @@used_names
  end
  
  private

    def generate_name(letters = 2, numebrs = 3)
      name = LETTERS.sample(letters).join + NUMBERS.sample(numebrs).join
      @@used_names.include?(name) ? generate_name(letters, numebrs) : name
    end
  
end
