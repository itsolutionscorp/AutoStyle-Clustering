class Robot
  
  attr_reader :name

  ASCII_IDX = 65
  LETTERS = 26

  def initialize
    reset
  end

  def reset
    generate_name
  end

  private

    def generate_name
      @name = [generate_alpha, generate_numeric].join
    end

    def generate_alpha
      rand(LETTERS ** 2).divmod(LETTERS).map { |l| (l + ASCII_IDX).chr }.join
    end

    def generate_numeric
      "%03d" % rand(10 ** 3)
    end
end
