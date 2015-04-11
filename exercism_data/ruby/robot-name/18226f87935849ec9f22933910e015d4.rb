class Robot
  class << self
    attr_writer :robot_names

    def robot_names
      @robot_names ||= []
    end
  end

  def name
    @name ||= generate_unique_name
  end

  def reset
    @name = nil
  end

  private

  def generate_unique_name
    loop do
      name = generate_random_name

      unless self.class.robot_names.include?(name)
        self.class.robot_names << name
        return name
      end
    end
  end

  def generate_random_name
    "#{random_letters}#{random_numbers}"
  end

  def random_letters
    random_chars_from_alphabet('A'..'Z', size: 2)
  end

  def random_numbers
    random_chars_from_alphabet(0..9, size: 3)
  end

  def random_chars_from_alphabet(alphabet, size: 0)
    alphabet = alphabet.to_a if alphabet.is_a? Range
    alphabet.sample(size).join
  end
end
