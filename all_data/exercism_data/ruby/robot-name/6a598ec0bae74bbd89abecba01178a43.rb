class Robot
  def self.new
    @@mama ||= MamaBot.new
    BabyBot.new(proc { @@mama.generate_unique_name })
  end

  private

  class MamaBot
    def initialize(name_generator = RandomStringGenerator.new)
      @registry = []
      @namer = name_generator
    end

    def generate_unique_name
      name = ''
      while name.empty?
        name = @namer.generate
        name = '' if @registry.include?(name)
      end
      @registry.push(name)
      name
    end
  end

  class BabyBot
    attr_reader :name

    def initialize(namer)
      @name = namer.call
      @namer = namer
    end

    def reset
      @name = @namer.call
    end
  end
end

class RandomStringGenerator
  attr_accessor :format, :letters, :numbers

  # format string:
  # @ for letter
  # # for number
  def initialize(format = '@@###', letters = ('A'...'Z'), numbers = (0..9))
    @format = format.chars
    @letters = letters.to_a
    @numbers = numbers.to_a
  end

  def format_map
    {
      '@' => letters,
      '#' => numbers
    }
  end

  def generate
    format.map do |el|
      format_map[el].sample.to_s
    end.join
  end
end

# Additional test added:
# def test_names_unique
#   names = []
#   10_000.times do
#     names.push(Robot.new.name)
#   end
#   assert names.uniq == names
# end
