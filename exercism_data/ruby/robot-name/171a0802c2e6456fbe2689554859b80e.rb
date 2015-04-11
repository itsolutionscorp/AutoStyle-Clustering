# The robot's name is to be assigned when it's created.
# The name must follow the following pattern: /\w{2}\d{3}/
# Remember, that \w is the same as [A-Za-z0-9_], but the README seems to
#   indicate that what they really want is /[A-Z]{2}\d{3}/
class Robot
  attr_reader :name

  def initialize
    set_name
  end

  def reset
    set_name
  end

  private

  def random_letter
    ('A'..'Z').to_a.sample
  end

  def random_number
    rand(0..9).to_s
  end

  def generate_name
    (2.times.map { random_letter } + 3.times.map { random_number }).join
  end

  def set_name
    name = generate_name
    if name_available?(name)
      @name = name
      @roster.puts @name
    else
      set_name
    end
  end

  def name_available?(name)
    r1 = Regexp.new(name)
    @roster ||= File.open('roster.txt', 'a+')
    @roster.grep(r1).empty?
  end
end
