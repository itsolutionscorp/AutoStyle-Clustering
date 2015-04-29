class Robot
  attr_reader :name

  USED_NAMES = []

  def initialize
    @name = generate_new_name
  end

  def reset
    @name = generate_new_name
  end

  private

  def generate_new_name
    (0..1).map { generate_letter }.join.concat(generate_number)
  end

  def generate_number
    rand(999).to_s
  end

  def generate_letter
    (65 + rand(25)).chr
  end

end
