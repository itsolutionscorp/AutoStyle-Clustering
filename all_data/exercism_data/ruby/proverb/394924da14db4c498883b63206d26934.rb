class Proverb
  attr_reader :things, :qualifier
  def initialize(*args)
    @things = args
    @qualifier = qualified? ? args.last[:qualifier] + " " : ""
  end

  def to_s
    build_proverb
  end

  private

  def qualified?
    things.last.class == Hash
  end

  def build_proverb
    message = ""
    (calculate_iterations).times do |i|
      message += normal_line(i)
    end
    message += last_line
  end

  def calculate_iterations
    qualified? ? things.length - 2 : things.length - 1
  end

  def normal_line(i)
    "For want of a #{things[i]} the #{things[i+1]} was lost.\n"
  end

  def last_line
    "And all for the want of a #{qualifier}nail."
  end

end
