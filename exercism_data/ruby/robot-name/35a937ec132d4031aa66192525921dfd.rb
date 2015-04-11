class Robot
  attr_reader :name, :letters_count, :digits_count

  def initialize
    @letters_count = 2
    @digits_count = 3
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def random_letters
    Array.new(letters_count) { [*'A'..'Z'].sample }.join
  end

  def random_digits 
    Array.new(digits_count) { [*'0'..'9'].sample }.join
  end

  def generate_name
    random_letters + random_digits
  end
end
