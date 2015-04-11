class Robot
  attr_reader :name

  LETTERS = %w{A B C D E F G H I J K L M N O P Q R S T U V W X Y Z}
  DIGITS = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    sprintf("%s%s%d%d%d", letter, letter, digit, digit, digit)
  end

  def letter
    LETTERS.sample
  end

  def digit
    DIGITS.sample
  end
end
