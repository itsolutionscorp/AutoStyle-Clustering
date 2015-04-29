class School
  def initialize
    @db = Hash.new { [] }
  end

  def db
    @db
  end

  def add(student_name, grade)
    db[grade] += [student_name]
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.keys.sort.reduce({}) do |sorted_school, grade|
      sorted_school.merge(grade => db[grade].sort)
    end
  end
end
