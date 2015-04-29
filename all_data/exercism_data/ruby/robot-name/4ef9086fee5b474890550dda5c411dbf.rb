class Robot
  LETTERS = ('A'..'Z').to_a
  NUMBERS = ('0'..'9').to_a

  def initialize
    @previous_names = []
  end

  def name
    @name ||= generate_new_name
  end

  def previously_named?(name)
    @previous_names.include?(name)
  end

  def reset
    @previous_names << name
    @name = nil
  end

  private

  def generate_new_name
    loop do
      name = generate_name
      return name unless previously_named?(name)
    end
  end

  def generate_name
    ''.tap do |name|
      2.times { name << LETTERS.sample }
      3.times { name << NUMBERS.sample }
    end
  end
end
