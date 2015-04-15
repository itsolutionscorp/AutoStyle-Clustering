class Garden
  PLANTS = { 'G' => :grass, 'C' => :clover, 'V' => :violets, 'R' => :radishes }
  STUDENTS = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
              "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

  def initialize(garden, students=[])
    @garden = garden
    students = (students.empty? ? STUDENTS : students.sort).map(&:downcase)
    build_garden(students)
  end

  private
  def build_garden(students)
    students.each_with_index do |student, i|
      rows = @garden.split
      break if i * 2 + 1 >= rows[0].size
      plantsStr = rows.map { |row| row[i * 2, 2] }.join
      plants = plantsStr.each_char.map { |c| PLANTS[c] }
      self.class.send(:attr_reader, student)
      instance_variable_set("@#{student}", plants)
    end
  end
end
