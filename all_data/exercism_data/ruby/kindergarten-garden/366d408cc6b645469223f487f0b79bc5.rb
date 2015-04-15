class Garden
  def initialize(diagram, students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"])
    @diagram_rows = diagram.split(/\n/)
    @plant_names = plant_names_to_symbols(@diagram_rows)
    methodize(students.sort.map { |n| n.downcase })
  end

  def methodize(students_array)
    students_array.each_with_index do |name, index|
      define_singleton_method(name) do
        assigned_plants = []
        position = index * 2
        @plant_names.each { |row| assigned_plants << row[position..position + 1] }
        return assigned_plants.flatten
      end
    end
  end

  def plant_names_to_symbols(diagram_rows)
    symbolized_diagram = []
    diagram_rows.map do |row|
      symbolized_diagram_row = []
      row.each_char do |char|
        case char
        when "C"
          symbolized_diagram_row << :clover
        when "G"
          symbolized_diagram_row << :grass
        when "R"
          symbolized_diagram_row << :radishes
        when "V"
          symbolized_diagram_row << :violets
        end
      end
      symbolized_diagram << symbolized_diagram_row
    end
    return symbolized_diagram
  end

end
