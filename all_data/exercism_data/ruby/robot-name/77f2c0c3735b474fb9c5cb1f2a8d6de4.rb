class Robot
  attr_accessor :name
  @@names = []
  Letters = ('A'..'Z').to_a
  Numbers = (0..10).to_a.map(&:to_s)

  def initialize
    @name = name_it!
  end

  def reset
    @name = name_it!
  end

  private
  def name_it!
    name = generate_a_name
    while @@names.include? name do
      name = generate_a_name
      @@names << name
    end
    @name = name
  end

  def generate_a_name
    string = ""
    2.times {string << Letters.sample}
    3.times {string << Numbers.sample}
    string
  end

end
