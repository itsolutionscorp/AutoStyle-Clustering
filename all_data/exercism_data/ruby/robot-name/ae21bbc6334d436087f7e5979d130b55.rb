class Robot

  LETTERS = [('a'..'z'), ('A'..'Z')].map { |i| i.to_a }.flatten
  def initialize
    @name = nil
  end
  def name
    @name ||= generate_name
  end
  def reset
    @name = nil
  end

  def generate_name
    first_part = (0...2).map {LETTERS[rand(LETTERS.length)]}.join
    second_part = rand(1000).to_s.rjust(3, '0')
    @name = first_part << second_part
  end

end

