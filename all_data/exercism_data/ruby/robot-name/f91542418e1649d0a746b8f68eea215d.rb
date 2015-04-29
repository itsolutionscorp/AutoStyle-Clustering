class Robot
  attr_accessor :name
  @@taken_names = Array.new

  def name
    @name ||= generate_random_name
  end

  def reset
    self.name = nil
  end

  def generate_random_name
    alphabet = [*'A'..'Z']
    numbers = [*0..9]
    random_alphabet = (0...2).map { alphabet.sample }
    random_numbers = (0...3).map { numbers.sample }
    @name = (random_alphabet + random_numbers).join
    name_taken? @name
    @name
  end

  def name_taken? name
    @@taken_names.include?(name) ? generate_random_name : @@taken_names << name
  end

  def self.taken_names
    @@taken_names
  end

end
