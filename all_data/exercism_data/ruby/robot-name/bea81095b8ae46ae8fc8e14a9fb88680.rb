require 'set'

class Robot
  attr_reader :name
  @@names = Set.new

  def initialize()
    setup
  end

  def reset
    setup
  end

  private
  def setup
    begin
      name = generate_name
    end until not @@names.include? name
    @name = name
    @@names << name
  end

  def generate_name
    (random_chars(2) + random_digits(3)).join
  end

  def random_chars(length)
    (("a".."z").to_a + ("A".."Z").to_a).sample(length)
  end

  def random_digits(length)
    (0 .. 9).to_a.sample(length)
  end
end
