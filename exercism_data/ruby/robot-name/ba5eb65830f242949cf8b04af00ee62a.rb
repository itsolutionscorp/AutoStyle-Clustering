class Robot
  @@LETTER_RANGE = 'A'..'Z'
  @@DIGIT_RANGE = '0'..'9'
  @@robots = []

  attr_reader :name

  def initialize()
    raise 'can\'t have more robots!' if @@robots.count == max_num_of_robot
    reset
  end

  def reset
    loop do
      @name = generate_name
      break unless @@robots.include?(@name)
    end
    @@robots << @name
  end

  # doesn't need in this case
  def destroy
    @name = nil
    @@robots.delete(@name)
  end

  private

  def generate_name
    random_string(@@LETTER_RANGE, 2) + random_string(@@DIGIT_RANGE, 3)
  end

  def random_string range, length
    length.times.map { range.to_a.sample }.join
  end

  def max_num_of_robot
    @@LETTER_RANGE.count**2 * @@DIGIT_RANGE.count**3
  end
end
