class Robot
  attr_reader :name

  @@robots = []
  DIGITS = (0..9).to_a
  CHARS = ('A'..'Z').to_a

  def initialize
    reset()
  end

  def reset
    old_name = @name
    @name = generate_name()
    @@robots.delete(old_name)
  end

  private

  def generate_name
    10.times do
      new_name = "#{generate_string(2,CHARS)}#{generate_string(3,DIGITS)}"
      next if @@robots.include?(new_name)

      @@robots.push(new_name)
      return new_name
    end
    raise "Did not found a unique new name."
  end

  def generate_string(length, from)
    length.times.map {from.sample}.join
  end


end
