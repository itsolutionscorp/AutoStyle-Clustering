class Robot
  def initialize
    @name = ""
  end

  def name
    @name = result if @name.empty?
    @name ||= result
  end

  def reset
    @name = ""
  end

  private

  def result
    (chars_generator + number_generator).join
  end

  def number_generator
    (0..9).to_a.shuffle[0,3]
  end

  def chars_generator
    alphabet.shuffle[0,2]
  end

  def alphabet
    [*('A'..'Z'), *('a'..'z')]
  end
end
