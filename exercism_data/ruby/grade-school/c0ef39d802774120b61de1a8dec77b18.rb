class School

  def to_hash
    students.sort.to_h
  end

  def add student, grade
    students[grade] ||= []
    students[grade] << student
    students[grade].sort!
  end

  def grade number
    students[number] || []
  end

  private

  def students
    @students ||= {}
  end

end
