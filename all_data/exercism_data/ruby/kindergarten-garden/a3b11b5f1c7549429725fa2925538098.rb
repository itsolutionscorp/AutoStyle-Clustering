class Garden
  attr_reader :plants, :students

  def initialize(plants, students=['alice', 'bob', 'charlie', 'david',
                                  'eve', 'fred', 'ginny', 'harriet',
                                  'ileana', 'joseph', 'kincaid', 'larry'])
    @plants = plants
    @students = students.sort
    determine_plant_owners
  end

  def determine_plant_owners
    students.each do |student|
      self.class.send(:define_method, student.downcase) {assign_plant_types(plants_for_student(student))}
    end
  end

  def plants_for_student(student)
    row1, row2 = plants.split("\n")
    position = students.index(student)
    first = position * 2
    second = first + 1
    row1[first..second] + row2[first..second]
  end

  def assign_plant_types(symbols)
    symbols.chars.collect {|symbol| plant_type(symbol)}
  end

  def plant_type(symbol)
    case symbol
    when 'G' then :grass
    when 'C' then :clover
    when 'R' then :radishes
    when 'V' then :violets
    end
  end
end
