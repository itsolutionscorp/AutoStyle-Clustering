class Robot
  attr_reader :name
  VALID_CHARS = [('a'..'z'),('A'..'Z')].map(&:to_a).flatten

  def initialize
    @@name_store ||= []
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private
    def rand_string length = 1, char_list = ('a'..'z').to_a
      (0...length).map{char_list[rand(char_list.length)]}.join
    end

    def random_name
      sprintf "%s%03d", rand_string(2, VALID_CHARS), rand(100)
    end

    def generate_name
      while @@name_store.include?(name = random_name)
      end

      @@name_store << name
      name
    end
end
