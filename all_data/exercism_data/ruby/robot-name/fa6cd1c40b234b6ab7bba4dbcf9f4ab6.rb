class Robot

  attr_reader :name
  attr_reader :previous_names

  def initialize
    @name = random_name
    self.previous_names = []
  end

  def reset
    log_name
    @name = unique_name
  end

  private

  attr_writer :previous_names

  def unique_name
    name = random_name
    return unique_name if previous_names.include?(name)
    name
  end

  def random_name
    two_random_letters.upcase + three_random_numbers.to_s
  end

  def two_random_letters
    random_letter + random_letter
  end

  def random_letter
    ('a'..'z').to_a[rand(26)]
  end

  def log_name
    self.previous_names << @name
  end

  def three_random_numbers
    rand(101..1000)
  end
end
