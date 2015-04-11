class Robot
  attr_reader :name

  ALPHABET = ('A'..'Z').to_a
  NAMES = ['']

  def initialize
    @name = ''
    reset
    NAMES << @name
  end

  def reset
    until !NAMES.include? @name
      @name = new_name
    end
  end

  private

  def new_name
    new_name = ''
    2.times do
      new_name += ALPHABET[rand(25)]
    end
    3.times do
      new_name += "#{rand(9)}"
    end
    new_name
  end
end
