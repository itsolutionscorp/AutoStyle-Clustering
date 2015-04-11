class Garden
  attr_reader :row1, :row2, :students

  def initialize(diagram, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry'])
    diagram   = diagram.split("\n")
    @row1     = diagram[0].chars
    @row2     = diagram[1].chars
    @students = students.collect{|s| s.downcase.to_sym}.sort
  end

  def range_for(student)
    start_at = students.index(student)*2
    end_at   = start_at + 1
    start_at..end_at
  end

  def codes_for(student)
    range = range_for(student)
    row1[range] + row2[range]
  end

  def method_missing(method_name)
    decrypter codes_for(method_name)
  end

  def decrypter(plant_codes)
    plant_codes.collect do |letter|
      case letter
      when 'V' then :violets
      when 'R' then :radishes
      when 'G' then :grass
      when 'C' then :clover
      end
    end
  end
end
