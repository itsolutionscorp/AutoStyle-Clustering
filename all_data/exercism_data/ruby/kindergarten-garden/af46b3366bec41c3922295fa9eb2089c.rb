class Garden
  DEFAULT_STUDENTS = %w[
    Alice Bob Charlie David Eve Fred
    Ginny Harriet Ileana Joseph Kincaid Larry
  ]

  def initialize(diagram, students = DEFAULT_STUDENTS)
    @diagram = Diagram.new(diagram)
    @students = students.sort
    define_student_methods
  end

  private

  def define_student_methods
    @students.each do |name|
      define_instance_method(name.downcase) do  # def alice
        plants_for(name)                        #   plants_for("Alice")
      end                                       # end
    end
  end

  def define_instance_method(*args, &block)
    self.class.send(:define_method, *args, &block)
  end

  def plants_for(child)
    index = @students.index(child)
    @diagram[index]
  end
end


class Garden
  class Diagram
    CUPS_PER_CHILD_PER_ROW = 2

    PLANTS = {
      "R" => :radishes,
      "C" => :clover,
      "G" => :grass,
      "V" => :violets
    }

    def initialize(pattern)
      @pattern = pattern
    end

    def [](index)
      offset = index * CUPS_PER_CHILD_PER_ROW
      plants = outer_row[offset, CUPS_PER_CHILD_PER_ROW] +
               inner_row[offset, CUPS_PER_CHILD_PER_ROW]
      plants.chars.map { |plant| PLANTS.fetch(plant) }
    end

    private

    def outer_row
      @pattern.lines.first
    end

    def inner_row
      @pattern.lines.last
    end
  end
end
