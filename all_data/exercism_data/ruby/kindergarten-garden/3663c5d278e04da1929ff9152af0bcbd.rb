class Garden
  attr_reader :plantings, :students

  def initialize(diagram, students=default_class)
    @plantings = parse_diagram(diagram)
    @students = parse_students(students).sort
  end

  private

  def parse_diagram(diagram)
    diagram.split("\n").map{ |row| row.chars }
  end

  def parse_students(students)
    students.map { |s| s.downcase.to_sym }
  end

  def student_garden_locations
    locations = {}
    students.each_with_index do |student, i|
      locations[student] = ((i * 2)..(i * 2 + 1))
    end
    locations
  end

  def method_missing(student)
    my_locations = student_garden_locations[student]
    my_plants = plantings.map { |row| row[my_locations] }
    unabbreviate(my_plants)
  end

  def unabbreviate(plants)
    plants.flatten.map { |plant| abbreviations[plant] }
  end

  def abbreviations
    { "R" => :radishes,
      "C" => :clover,
      "G" => :grass,
      "V" => :violets
    }
  end

  def default_class
    [ :alice, :bob, :charlie, :david,
      :eve, :fred, :ginny, :harriet,
      :ileana, :joseph, :kincaid, :larry]
  end

end
