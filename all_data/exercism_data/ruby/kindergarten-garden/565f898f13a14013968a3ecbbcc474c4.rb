class Garden
  class Diagram
    def self.short_code_to_plant_name(short_code)
      {
        'R' => :radishes,
        'C' => :clover,
        'G' => :grass,
        'V' => :violets
      }[short_code]
    end

    attr_reader :layout
    def initialize(plant_map)
      @layout = plant_map.split.map { |row| row.split '' }
    end

    def plants_in_columns(*columns)
      layout.map do |row|
        columns.collect { |column| row[column] }
      end.flatten.map do |short_code|
        Diagram.short_code_to_plant_name(short_code)
      end
    end
  end

  class Students
    attr_reader :names
    def initialize(names = [])
      @names = names.sort.map { |student| student.downcase }
    end

    def cups_for_students(name)
      if(names.empty?)
        start = (name.downcase[0].ord - 97)*2
      else
        start = names.index(name)*2
      end
      [start, start+1]
    end
  end

  attr_reader :students, :diagram
  def initialize(diagram_string, students=[])
    @diagram = Diagram.new(diagram_string)
    @students = Students.new(students)
  end

  private

  def plants_for_student(student)
    diagram.plants_in_columns(*students.cups_for_students(student) )
  end

  def method_missing(name, *args)
    plants_for_student(name.to_s)
  end
end
