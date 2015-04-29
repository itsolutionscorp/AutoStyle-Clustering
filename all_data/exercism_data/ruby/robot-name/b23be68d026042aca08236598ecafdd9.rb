class Robot
  attr_reader :name

  LETTERS = ('a'..'z').to_a + ('A'..'Z').to_a

  def initialize
    reset
  end

  def reset
    @name = new_name
  end

  private

  def new_name
    "#{LETTERS.sample}#{LETTERS.sample}#{rand(0..999).to_s.rjust(3, '0')}"
  end
end
