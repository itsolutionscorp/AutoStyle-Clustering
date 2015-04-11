class Garden
  PLOT_SIZE = 2
  STUDENTS = %w[Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry]
  PLANTS = {"R" => :radishes, "C" => :clover, "G" => :grass, "V" => :violets}

  def initialize(diagram, students = nil)
    @students = (students || STUDENTS).sort.map { |student|
      student.downcase.to_sym
    }
    @diagram = diagram.split("\n")

    @students.each.with_index do |student, i|
      define_singleton_method student do
        translate(get_plot(i))
      end
    end
  end

  def get_plot(n)
    @diagram.each.with_object("") do |row, result|
      start_index = n * PLOT_SIZE
      result << row[start_index, PLOT_SIZE]
    end
  end

  def translate(abbreviations)
    abbreviations.chars.map { |abbreviation|
      PLANTS[abbreviation]
    }
  end
end
