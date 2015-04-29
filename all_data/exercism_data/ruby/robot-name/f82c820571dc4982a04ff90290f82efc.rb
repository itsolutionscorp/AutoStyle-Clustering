class Robot
  attr_reader :name

  @@names = []

  def initialize
    reset
  end

  def reset
    @name = [
      random_letter,
      random_letter,
      random_number,
      random_number,
      random_number
    ].join

    if @@names.include?(@name)
      reset
    else
      @@names << @name
    end
  end

  private

  def random_letter
    ('A'..'Z').to_a[rand(26)]
  end

  def random_number
    rand(10)
  end
end
