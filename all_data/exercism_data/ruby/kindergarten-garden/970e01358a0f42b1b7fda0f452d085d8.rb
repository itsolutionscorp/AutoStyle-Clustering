class Garden
  attr_reader :rows, :students

  STUDENT_NAMES = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

  def initialize(garden_plan, students=STUDENT_NAMES)
    @rows = garden_plan.split("\n")
    @students = students.sort
  end

  def method_missing(method_name)
    formated_method_name = method_name.to_s.capitalize
    index = students.find_index(formated_method_name)

    starting_num = 0
    starting_num = (index*2).times do |n|
      starting_num += n
    end

    plant_numbers = [starting_num, (starting_num + 1)]
    find_plants(plant_numbers)
  end

  def letter_assignment(letter)
    case letter
    when 'V'
      :violets
    when 'R'
      :radishes
    when 'C'
      :clover
    when 'G'
      :grass
    end
  end

  def find_plants(plants)
    letters = rows.map do |row|
      [ row[plants[0]], row[plants[1]] ]
    end.flatten

    letters.collect do |letter|
      letter_assignment(letter)
    end
  end
end
