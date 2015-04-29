class School

  def add(student, grade)
    students[grade] << student
    self
  end

  def grade(grade_number)
    students[grade_number].sort
  end

  def to_hash
    students.keys.sort.each_with_object({}) do |grade_number, hash|
      hash[grade_number] = grade(grade_number)
    end
  end

private

  def students
    @students ||= Hash.new { |hash, key| hash[key] = [] }
  end

end
