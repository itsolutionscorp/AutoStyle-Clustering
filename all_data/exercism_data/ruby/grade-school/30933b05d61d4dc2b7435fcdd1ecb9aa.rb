class School
  def initialize name
    @db = {}
  end

  def add student_name, grade
    @db[grade] ||= []
    @db[grade] << student_name
  end

  def grade level, sorted_by: :time
    students = @db.fetch(level, [])

    case sorted_by
    when :time then students.clone
    when :alphabet then students.sort
    end
  end

  def grades
    @db.keys.sort
  end

  def db
    grades.each_with_object({}) do |level, school|
      school[level] = grade(level, sorted_by: :time)
    end
  end

  def sort
    grades.each_with_object({}) do |level, school|
      school[level] = grade(level, sorted_by: :alphabet)
    end
  end
end
