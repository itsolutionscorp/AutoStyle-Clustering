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
    ((("a".."z").to_a + ("A".."Z").to_a).sample(2) + ((0..9).to_a.sample(3))).join
  end
end
