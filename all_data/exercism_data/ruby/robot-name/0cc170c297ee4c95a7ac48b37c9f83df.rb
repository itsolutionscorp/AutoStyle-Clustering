class Robot
  attr_reader :name

  def initialize
    @@used_names = []
    @name = new_name
  end

  def new_name
    name = random_code
    loop do
      if used_name?(name)
        name = random_code
      else
        break
      end
    end
      @@used_names << name
      name
  end

  def used_name?(name)
    @@used_names.any?{|code| code == name}
  end

  def random_code
    "#{random_letter}#{random_letter}#{random_number}#{random_number}#{random_number}"
  end

  def random_number
    numbers = *(0..9)
    numbers.sample
  end

  def random_letter
    letters = *("a".."z")
    letters.sample
  end

  def reset
    @name = new_name
  end
end
