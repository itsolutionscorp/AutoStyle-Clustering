class Robot
  attr_reader :name
  ALPHABET = ("A".."Z").to_a.freeze
  NUMBERS = (0..9).to_a.freeze

  @@taken_names = [] 

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    loop do
      name = ALPHABET.sample(2).join + NUMBERS.sample(3).join
      unless @@taken_names.include?(name)
        @@taken_names << name
        return name
      end
    end
  end
end
