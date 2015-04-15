class School
  def grades_students
    @students ||= Hash.new do |hash, key|
      hash[key] = []
    end
  end

  def add(name, grade)
    grades_students[grade] << name
  end

  def to_hash
    grades_students.sort.each_with_object(
      {}
    ) do |(grade, students), hash|
      hash[grade] = students.sort
    end
  end

  def grade(grade)
    grades_students[grade].sort
  end
end
