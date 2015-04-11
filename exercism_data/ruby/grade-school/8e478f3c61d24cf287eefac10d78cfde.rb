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
    hash = {}
    grades_students.sort.each do |key, grade|
      hash[key] = grade.sort
    end
    hash
  end

  def grade(grade)
    grades_students[grade].sort
  end
end
