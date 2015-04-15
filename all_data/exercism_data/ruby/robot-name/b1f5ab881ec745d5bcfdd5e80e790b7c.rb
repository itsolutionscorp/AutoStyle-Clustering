class Robot
  require 'set'
  
  attr_reader :name

  ASCII_IDX = 65
  LETTERS = 26

  @@names_generated = Set.new

  def initialize
    reset
  end

  def reset
    generate_name
    reset if @@names_generated.include? @name
  end

  private

    def generate_name
      @name = [generate_alpha, generate_numeric].join
    end

    def generate_alpha
      rand(LETTERS ** 2).divmod(LETTERS).map { |l| (l + ASCII_IDX).chr }
    end

    def generate_numeric
      "%03d" % rand(10 ** 3)
    end
end
