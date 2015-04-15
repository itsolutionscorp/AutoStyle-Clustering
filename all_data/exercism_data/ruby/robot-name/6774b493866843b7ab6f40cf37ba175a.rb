class Robot
  attr_accessor :name
  ALPHA = ('A'..'Z').to_a
  NUMER = ('0'..'9').to_a
  @@names = []

  def initialize
    @name = create_name
  end

  def reset
    @name = create_name
  end

  private

  def create_name
    name = ''
    2.times do
      name += ALPHA[(rand*ALPHA.length).floor]
    end
    3.times do
      name += NUMER[(rand*NUMER.length).floor]
    end
    unless @@names.include?(name)
      @@names << name
      return name
    else
      create_name
    end
  end

end
