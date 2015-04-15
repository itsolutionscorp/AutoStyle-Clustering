class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    initialize
  end

  private

  def generate_name
    name = ""

    5.times do |i|
      if i < 2
        name << rand(65..90).ord
      else
        name << rand(0..9).to_s
      end
    end

    name
  end
end
