class School

  def to_hash
    students
  end

  def add student, grade
    students[grade] = [] unless students[grade]
    students[grade] << student
    sort!
  end

  def grade number
    students[number].nil? ? [] : students[number]
  end

  private

  def students
    @students ||= {}
  end

  def sort!
    @students = students.sort.map {|student| [student[0], student[1].sort] }.to_h
  end
end
