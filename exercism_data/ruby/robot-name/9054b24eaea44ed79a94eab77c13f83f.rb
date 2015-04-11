class Robot

  def initialize
    assign_new_name
  end

  def reset
    assign_new_name
  end

  attr_reader :name

  private

  def assign_new_name
    @name = generate_name
  end

  def generate_name
    "#{alpha_portion}#{numeric_portion}"
  end

  ALPHA_LENGTH = 2
  NUMERIC_LENGTH = 3
  NUM_LETTERS = 26

  def alpha_portion
    (1..ALPHA_LENGTH).each_with_object("") do |_, alpha|
      alpha << random_capital_letter
    end
  end

  def numeric_portion
    (1..NUMERIC_LENGTH).each_with_object("") do |_, numeric|
      numeric << random_digit
    end
  end

  def random_capital_letter
    ("A".ord + rand(NUM_LETTERS - 1)).chr
  end

  def random_digit
    rand(9).to_s
  end

end
