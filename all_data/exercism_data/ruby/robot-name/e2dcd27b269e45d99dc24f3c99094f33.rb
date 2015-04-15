class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    self.name = Robot.next_name
  end

  private

  attr_writer :name

  class << self
    def next_name
      throw 'All names in use' if names.empty?
      names.pop
    end

    private

    def names
      @names ||= generate_all_possible_names
    end

    def generate_all_possible_names
      all_names = []
      [*'A'..'Z'].repeated_permutation(2).map do |letters|
        [*'0'..'9'].repeated_permutation(3).map do |digits|
          all_names << (letters + digits).join('')
        end
      end
      all_names.shuffle
    end
  end
end
