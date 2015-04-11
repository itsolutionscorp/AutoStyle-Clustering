class Robot
  ALPHABET = [*'A'..'Z']
  attr_reader :name

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private
  def generate_name
    @name = "%s%s%03d" % [
      ALPHABET[seed % 26],
      ALPHABET[seed / 26 % 26],
      seed % 1000
    ]
  end

  def seed
    time = Time.now
    time.to_i + time.object_id
  end
end
