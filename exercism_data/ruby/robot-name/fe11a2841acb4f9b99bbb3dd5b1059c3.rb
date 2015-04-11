class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    Robot.release_name(name)
    self.name = Robot.next_name
  end

  private

  attr_writer :name

  class << self
    def next_name
      throw 'All names in use' if names.empty?
      names.pop
    end

    def release_name(name)
      released_names.push(name) if name
    end

    private

    attr_writer :released_names

    def released_names
      @released_names ||= []
    end

    def names
      @names ||= generate_all_possible_names
      if @names.empty? && released_names.any?
        # reverse so that reuse old released names in order released
        @names = released_names.reverse
        self.released_names = []
      end
      @names
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
