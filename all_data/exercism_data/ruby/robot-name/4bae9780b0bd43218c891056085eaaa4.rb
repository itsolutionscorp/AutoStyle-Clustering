class Robot

  attr_accessor :name
  @@robot_names = %w()

  def initialize

    # As there is a limited combinatorial space of the names, maybe a check for 'fullness'
    # should be done otherwise we can get stuck in the loop...
    begin
      name = generate_name
    end while @@robot_names.include? name

    @@robot_names << @name = name
  end

  def reset
    @@robot_names.delete(@name)
    initialize
  end

  private

  def generate_name
    characters = (0...2).map { ('A'..'Z').to_a[rand(26)] }.join
    number = "%03d" % rand(999)

    # rand(2) == 0 ? characters = 'AA' : characters = 'BB'
    # number = '000'

    return characters+number
  end


end
