class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    generate_letters + generate_numbers
  end

  def generate_letters
    [*('a'..'z'),*('A'..'Z')].shuffle.take(2).join
  end

  def generate_numbers
    Random.rand(999).to_s.rjust(3,'0')
  end
end
