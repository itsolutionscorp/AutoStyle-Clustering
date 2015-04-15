class Robot
  attr_reader :name

  def initialize
    assign_name
    add_name
  end

  def reset
    remove_name
    initialize
  end

  def to_s
    @name
  end

  private
    @@num_chars, @@num_digits = 2, 3
    @@num_possible = 26**@@num_chars + 10**@@num_digits
    @@num_possible -= 1 if @@num_chars < 1
    @@num_possible -= 1 if @@num_digits < 1
    @@chars = ('A'..'Z').to_a
    @@digits = ('0'..'9').to_a

    @@names = {}

    def assign_name
      raise Exception.new('out of names') if out_of_names?
      loop do
        @name = random_chars(@@num_chars) + random_digits(@@num_digits)
        break if name_unique?
      end
    end

    def random_chars(n)
      (@@chars*n).sample(n).join
    end

    def random_digits(n)
      (@@digits*n).sample(n).join
    end

    def add_name
      @@names[@name] = true
    end

    def remove_name
      @@names[@name] = nil
    end

    def name_unique?
      @@names[@name] == nil
    end

    def out_of_names?
      @@names.length >= @@num_possible
    end
end
