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
    (0..1).map { generate_letter }.join.concat(generate_number)
  end

  def generate_number
    rand(999).to_s
  end

  def generate_letter
    (65 + rand(25)).chr
  end

end
