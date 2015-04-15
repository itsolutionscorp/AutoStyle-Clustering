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
    @@names = []
    @@chars, @@digits = 2, 3

    def assign_name
      raise Error('not so fast, robot overlord!') if @@names.length >= 26**@@chars + 10**@@digits
      loop do
        @name = random_chars(@@chars) + random_digits(@@digits)
        break if name_unique?
      end
    end

    def random_chars(n)
      ('A'..'Z').to_a.shuffle[0,n].join
    end

    def random_digits(n)
      ('0'..'9').to_a.shuffle[0,n].join
    end

    def name_unique?
      !@@names.include? @name
    end

    def add_name
      @@names << @name
    end

    def remove_name
      @@names.delete(@name)
    end
end
