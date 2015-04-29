class Robot
  @@names = []

  LETTERS = ('A'..'Z').to_a + ('a'..'z').to_a
  NUMBERS = (0..9).to_a
  PATTERN = {
    2 => LETTERS,
    3 => NUMBERS
  }

  def name
    @name ||= create_uniq_name
  end

  def reset
    @name = nil
  end

  private

  def create_uniq_name
    name = PATTERN.map { |num, chars| chars.sample(num) }.join
    if @@names.include?(name)
      create_uniq_name
    else
      @@names << name
      name
    end
  end
end
